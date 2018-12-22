# HelloFlux

![captcha.gif](https://raw.githubusercontent.com/mzkii/HelloFlux/master/captcha.gif)

`local.properties` に [OAuth Apps](https://github.com/settings/developers) で作成した `Client ID` と `Client Secret`，

さらに `Authorization callback URL` (REDIRECT_SCHEME://REDIRECT_HOSTの形式)を以下の形式で追加してください． 

``` local.properties
CLIENT_ID=xxxxxxxxxxxxxxxxxxxx
CLIENT_SECRET=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
REDIRECT_SCHEME=xxxxxxxxxxxxxxxxxxxx
REDIRECT_HOST=xxxxxxxxxxxxxxxxxxxx
```
