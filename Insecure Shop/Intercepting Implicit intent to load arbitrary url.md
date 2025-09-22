In order to intercept an intent it has to be broadcasted first, so searching the InsecureShop for instances of sendBroadcast revealed the file `ProductAdapter` where a broadcast is being created with the product url as soon as you click on the 'more info' button.

```java
holder.getMBinding().moreInfo.setOnClickListener(new View.OnClickListener() { // from class: com.insecureshop.ProductAdapter$onBindViewHolder$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Intent intent = new Intent("com.insecureshop.action.PRODUCT_DETAIL");
                intent.putExtra("url", ProductDetail.this.getUrl());
                context.sendBroadcast(intent);
            }
        });
```
This intent is supposed to be received by the receiver registered at the file `ProductListActivity`:
```java
        IntentFilter intentFilter = new IntentFilter("com.insecureshop.action.PRODUCT_DETAIL");
        registerReceiver(this.productDetailBroadCast, intentFilter);
        ProductAdapter productAdapter = new ProductAdapter();
```
Inspecting the class ProductDetailBroadCast which is the class of the receiver, has the following code:
```java
 public void onReceive(Context context, Intent intent) {
        Intent webViewIntent = new Intent("com.insecureshop.action.WEBVIEW");
        webViewIntent.putExtra("url", "https://www.insecureshopapp.com/");
        if (context != null) {
            context.startActivity(webViewIntent);
        }
    }
```
