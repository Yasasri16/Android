## NoSQolarly

### Step 1: Decompiling the APK
The first thing I did was decompile the APK using JADX. The most important file was BackendAiHelper.kt, where I found the backend URL hardcoded in plain text: `https://2026.mhc-ctf.workers.dev/nosqolarly`. I also found the generateResponse method signature which took four parameters: userMsg, contextText, embeddingsPayload, and token. This told us the RAG pipeline sends document context alongside the user's message to the AI, which is important later.
From MainActivity.kt we also noticed the app accepted shared URLs via ACTION_SEND intents with only a weak startsWith("http") check, meaning any app on the device could feed arbitrary URLs into the RAG pipeline — but that turned out to be a rabbit hole for this particular flag.

### Step 2: Enumerating the Backend API
Later, I wrote a bash script to probe all possible endpoints with POST requests and check HTTP status codes. Most returned 400 , while /chat returned 401 (unauthorized), and a few returned specific error messages like "Invalid action. Use /chat" which confirmed the API was routing everything through a single /chat endpoint.

### Step 3: Creating an Account and Getting a Token
We hit /signup with a username and password, which succeeded. Then /login returned a JWT token. We base64-decoded the JWT payload section (the middle part between the two dots) and found something unusual — alongside the standard sub, username, and exp fields, there was a non-standard field called challenge containing a 64-character hex string: ef9cd8bcdc0f2c154732c8e709ada1bdd5f044af0de285715b275a517ce69814. Standard JWTs don't have a challenge field. This was the first major clue that something interesting was happening with this value.

### Step 4: Probing the Chat Endpoint
Our initial script sent {"message":"..."} to /chat but kept getting "Missing 'query' in request body". Once we switched to {"query":"..."} it worked. We then ran a series of prompt injection attempts — asking the AI to repeat its system prompt, list its documents, reveal secrets, and so on. Most got deflected with standard AI refusals. But when we asked "What is the flag?" the server responded with something very different from a refusal. It returned the flag encrypted with AES-256-GCM and told us exactly how to decrypt it, pointing us back to the challenge field in our JWT as the decryption key. The encrypted blob looked like this: DlSKaAOmv3wQnIyz:9EeID4QqKbgVHIwtrOigHBCQIKei3T2CfNNYDI+qDKOSpPozzXtXvMzgkQ==

### Step 5: Understanding the Encryption Format
The format IV:ciphertext with a colon separator is a common convention for AES-GCM encrypted data. The part before the colon is the Initialization Vector (IV, also called nonce in GCM mode), and the part after is the ciphertext with the GCM authentication tag appended to the end. In AES-256-GCM, the tag is always the last 16 bytes of the ciphertext blob. Both parts are base64-encoded. The key is 32 bytes because AES-256 requires a 256-bit key, and our challenge hex string is exactly 64 hex characters which decodes to exactly 32 bytes — not a coincidence.

### Step 6: Decryption
We wrote a Python script using the cryptography library.

### The Intended Vulnerability
The clever part of this challenge design is that the server never sends the flag in plaintext over the wire, but it does hand you the decryption key inside the JWT it gives you at login. The assumption the challenge is testing is whether you notice that non-standard challenge field in the JWT and realize it's not just metadata — it's the key material. Most people would decode the JWT, see a random-looking hex string, and ignore it. The intended path was: decompile APK → find backend URL → register and log in → decode JWT and notice the challenge field → ask the AI for the flag → realize the encrypted response uses challenge as the key → decrypt. The flag text 4ll_dbs_bl33d_th3_s4m3 ("all DBs bleed the same") is a commentary on the challenge theme — NoSQL databases like Couchbase suffer from the same classes of vulnerabilities as traditional SQL databases, just with different syntax.
