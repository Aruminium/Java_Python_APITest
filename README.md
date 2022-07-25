# Java と Python で HTTPリクエスト/レスポンス

目的: JavaでHTTPリクエスト & PythonでHTTPレスポンスできるようにする

疑問点・間違いはissueでお願いします。

## AWS等のIaas,Paasを用いる場合
```
EC2上にAPIサーバーを構築する場合に気をつけるのは間違ってる可能性ありますが…

Java側
・host名 localhost -> インスタンスのpublic IPにする
http://(public IP):5000/~

Python側
・http://app.run(port=5000, host='0.0.0.0')にしてセキュリティグループでアクセス制限をする


Javaをaws上に置くにはhttpsでないといけない(もしかしたらregionも東京の必要があるかも)

のでEC2使うならRoute53等でhttpsにリダイレクトさせる

もしくはLambda と APIGateWayとかを使うのもありだと思う(試してないけど)
```
