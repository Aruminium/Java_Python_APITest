# Java と Python Linebot スターターキット

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

<img width="167" alt="スクリーンショット 2022-08-08 17 25 55" src="https://user-images.githubusercontent.com/73931800/183374064-17ae157e-a015-4c29-bf79-0a13911bf009.png">

## linebot.env を Java_Python_Linbot_Starters_kit/spring-app/ に作成する
<img width="167" alt="スクリーンショット 2022-08-08 17 25 55" src="https://user-images.githubusercontent.com/73931800/183374064-17ae157e-a015-4c29-bf79-0a13911bf009.png"> 
