# Docker による Java と Python Linebot スターターキット 

目的: Javaをバックエンド、PythonをAPIサーバーとしてLinebotをすぐに構築できるようにする

使い方(後日更新)

1. [Line Developer](https://developers.line.biz/ja/)にアクセス
2. コンソール > Lineアカウントでログイン
3. 「プロバイダー」横の作成ボタンを押し、任意の名前で作成する。
<img width="1122" alt="スクリーンショット 2022-08-08 17 05 45" src="https://user-images.githubusercontent.com/73931800/183370315-ea62c301-af7f-4d99-96a8-08beeb2b7556.png">
4. 「Messagin API」をクリックし情報を入力する。ただし「プライバシーポリシーURL」と「サービス利用規約URL」は未入力でOK
<img width="643" alt="スクリーンショット 2022-08-08 17 09 46" src="https://user-images.githubusercontent.com/73931800/183371283-0df521d3-18be-42c8-ae3c-5f3951bd04d5.png">
5. 管理画面に遷移したらひとまず完了
<img width="647" alt="スクリーンショット 2022-08-08 17 16 15" src="https://user-images.githubusercontent.com/73931800/183372999-7f20ea28-a479-42ac-ae4e-989e3e807397.png">

6. 次に任意の場所でgit cloneする移動する($は以降にコマンドが続くという意味)

```shell
$ git clone https://github.com/Aruminium/Java_Python_Linbot_Starters_kit
$ cd Java_Python_Linbot_Starters_kit/
```

7. 環境変数ファイル .env linebot.env db.env　を作成する

## .env を Java_Python_Linbot_Starters_kit/に作成する

```.env
PMA_ARBITRARY=1
PMA_HOST=mysql
PMA_USER=root
PMA_PASSWORD=root
```

### ディレクトリイメージ

<img width="167" alt="スクリーンショット 2022-08-08 17 25 55" src="https://user-images.githubusercontent.com/73931800/183374064-17ae157e-a015-4c29-bf79-0a13911bf009.png">

## linebot.env を spring-app/src/main/resources/ に作成する

```linebot.env
LINEBOT_CH_TOKEN=長期アクセストークン
LINEBOT_CH_SECRET=チャンネルシークレット
HANDLER_PATH=/callback
```

- 長期アクセストークンは先のLine Developersの管理画面 > Messaging API設定の一番下の「チャネルアクセストークン」から発行してコピペする
- チャンネルシークレットは先のLine Developersの管理画面 > チャンネル基本設定の下近くに記載されているので、コピペする

これらの情報は絶対に他人に教えない！悪用されるリスクが発生します。

<img width="1015" alt="スクリーンショット 2022-08-08 17 34 13" src="https://user-images.githubusercontent.com/73931800/183376506-8d36f1d2-fe9e-4f81-8429-aea18c7f53e1.png">

<img width="536" alt="スクリーンショット 2022-08-08 17 34 01" src="https://user-images.githubusercontent.com/73931800/183376739-31b78b4c-51bb-47f6-a3c6-fea6542dcc2b.png">

### ディレクトリイメージ

<img width="183" alt="スクリーンショット 2022-08-08 17 32 25" src="https://user-images.githubusercontent.com/73931800/183375244-56a8ff31-944d-4a6f-a55b-93afee34ad21.png">

## db.env を mysql/ に作成する

```db.env
MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=test_db
MYSQL_USER=dev_usr
MYSQL_PASSWORD=dev_usr_pass
TZ="Asia/Tokyo"
```

### ディレクトリイメージ
<img width="180" alt="スクリーンショット 2022-08-08 17 45 12" src="https://user-images.githubusercontent.com/73931800/183377864-c41cdee8-1dfc-4ba0-af1e-3acbfe402b13.png">

8. docker-compose up -d でコンテナを立ち上げよう

```shell
$ docker compose up -d
```

目安としてダウンロードしたDocker Desktopが以下の通りになっていたら成功しています。
※成功していない時は、envファイルに問題がある可能性があります。

<img width="730" alt="スクリーンショット 2022-08-08 17 48 14" src="https://user-images.githubusercontent.com/73931800/183378427-5f203bf8-cf88-4f96-9fce-56e6b799f8c7.png">

spring-app コンテナの起動には時間がかかります。spring-appをクリックし、以下の状態になっていることを確認する。

9. ngrok でHttps化する
ngrokというアプリケーションを用いて、localhost:8080をssl化させます
これによってLinebotと連携することができます。

[ngrok](https://ngrok.com/download)からinstallしてください

macの場合は

```shell
$ brew install ngrok/ngrok/ngrok
```

windowsの場合は

```shell
$ choco install ngrok
```

上記のコマンドからのinstallではなくzipファイルをダウンロードする場合は
解凍したファイル内のngrokファイルを開く

以下のコマンドを実行しhttps化をさせます。

```shell
$ ngrok http -region jp 8080
```

実行すると https://〇〇.jp.ngrok.io と表示されるのでコピーする
<img width="535" alt="スクリーンショット 2022-08-08 17 59 30" src="https://user-images.githubusercontent.com/73931800/183380839-0dba853e-78ae-4c6c-bb77-c9003439aed7.png">

10. Line Developersで先のURLを入力する

先のLine Developersの管理画面 > Messaging API設定のWebhook設定に以下のように設定する

```
先ほどのURL/callback
```

/callbackを付け加えるのを忘れずに

<img width="710" alt="スクリーンショット 2022-08-08 18 02 40" src="https://user-images.githubusercontent.com/73931800/183381309-75977179-9bd8-4a33-a6c5-ada54d4d52eb.png">

設定し、検証ボタンをクリック"成功"と表示されれば晴れてBotの完成だ

<img width="699" alt="スクリーンショット 2022-08-08 18 07 57" src="https://user-images.githubusercontent.com/73931800/183382624-350d62f4-66ff-4e34-9808-6526d867da24.png">

webhookの利用をONにする

<img width="253" alt="スクリーンショット 2022-08-08 18 12 26" src="https://user-images.githubusercontent.com/73931800/183383197-7855d6d5-3f2c-4596-8ed8-3fd85aee0dfd.png">

次にLINE公式アカウント機能 > 応答メッセージ > 編集　を押す

<img width="715" alt="スクリーンショット 2022-08-08 18 13 27" src="https://user-images.githubusercontent.com/73931800/183383400-6a23bf0f-48f2-4c3c-ae29-ec8b65678859.png">

あいさつメッセージと応答メッセージをOFFにする

<img width="627" alt="スクリーンショット 2022-08-08 18 14 40" src="https://user-images.githubusercontent.com/73931800/183383706-28e2fe65-0cb4-4aca-bc1c-e0e9f7f0ff20.png">


11. 友達追加して完了

Messaging API設定にQRコードが貼られているので、そこから友達追加をする。

<img width="581" alt="スクリーンショット 2022-08-08 18 06 26" src="https://user-images.githubusercontent.com/73931800/183382486-389f870b-e6ea-4cf3-b928-d22ffc128322.png">

## 試してみる

AIは実装していないので、"デフォルト"と返すようにしている

<img width="373" alt="スクリーンショット 2022-08-08 18 18 03" src="https://user-images.githubusercontent.com/73931800/183384371-1caaa286-14e6-475d-9c3c-937a56dd4e6f.png">

# 拡張するには?
Python/server.py と
spring-app/ を更新する必要がありますが、AI推定をさせたい場合はserver.pyのみを編集してください
