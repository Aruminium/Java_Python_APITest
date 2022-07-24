from flask import Flask, jsonify, request
import ast

# おまじない
app = Flask(__name__)
app.config["JSON_AS_ASCII"] = False

# http://localhost:5000/test にPOSTリクエストが来たら実行→レスポンスを返す
@app.route("/test", methods=['POST'])
def res_EstimationResult():
    # デシリアライズ(ただの文字列になる)
    req = request.get_json()

    # <class 'dict'>であることを確認する
    print(type("aa"))

    # <class 'str'>の場合 以下で str -> dict へ変換する
    # req = ast.literal_eval(req)

    # {'key': 'value'}を表示
    print(req)

    # res = res[key] 複数のデータを返す場合jsonifyを使うためこの処理は不要
    res = req["message"]

    # ここでAIで推定させる関数を呼び出して上げる. "これは犬です"の様な
    # res = AIに推定させる関数(res)

    return res

# 実行 http://localhost:5000
if __name__ == "__main__":
    # port番号5000番でデバッグモードON(ファイル変更時に自動でリロードしてくれる)
    app.run(port=5000, debug=True)
    # デフォルトはhttp://127.0.0.1 (自分のみアクセスできる)で
    # これを0.0.0.0(誰でもアクセス)にするときはapp.run(port=5000, debug=True, host='0.0.0.0')
    # 例えばEC2にAPIサーバを置く場合は試してみるといいかも

# jsonifyを使うと辞書型をjson形式に変換でき、
# response headerをapplication/jsonで返してくれる
# でも例えば「これは〜です」resを返すだけなら使う必要はなさそう