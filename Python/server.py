from flask import Flask, jsonify, request, Response, redirect, url_for
import cv2
import base64
import numpy as np
import ast
from siritori import Siritori

# おまじない
app = Flask(__name__)
app.config["JSON_AS_ASCII"] = False
siritori = Siritori("output.txt")

# http://localhost:5000/message にPOSTリクエストが来たら実行→レスポンスを返す
@app.route("/message", methods=['POST'])
def res_EstimationResult():
    # デシリアライズ(ただの文字列になる)
    req = request.get_json()

    # <class 'dict'>であることを確認する
    print(type(req))

    # <class 'str'>の場合 以下で str -> dict へ変換する
    # req = ast.literal_eval(req)

    # {'key': 'value'}を表示
    print(req)

    # res = res[key]
    res = req["message"]

    # ここで自然言語処理させる
    # res = 自然言語処理をさせる関数(res)
    res = "デフォルト"

    return res

# http://localhost:5000/image にPOSTリクエストが来たら実行→レスポンスを返す
@app.route("/image", methods=['POST'])
def res_ImgProcessingResult():


    # デシリアライズ(ただの文字列になる)
    req = request.get_json()

    # <class 'dict'>であることを確認する
    print(type(req))

    # <class 'str'>の場合 以下で str -> dict へ変換する
    # req = ast.literal_eval(req)

    # {'key': 'value'}を表示
    print(req)

    # res = res[key]
    binary = req["image"]

    #base64 から 画像へデコードする
    filename = "decode.jpg"
    img_binary = base64.b64decode(binary)
    jpg = np.frombuffer(img_binary, dtype=np.uint8)
    # raw image <- jpg
    img = cv2.imdecode(jpg, cv2.IMREAD_COLOR)
    # 画像出力
    cv2.imwrite(filename, img)

    # ここで画像関係のAIの関数を呼び出す. "これは犬です"と推定させる様な
    # res = AIに推定させる関数(filename)
    res = "デフォルト"
    return res

@app.route("/siritori", methods=['POST'])
def siritori_game():
    global siritori
    col = False
    # デシリアライズ
    req = request.get_json()

    # <class 'dict'>であることを確認する
    print(type(req))

    # <class 'str'>の場合 以下で str -> dict へ変換する
    # req = ast.literal_eval(req)

    # {'key': 'value'}を表示
    print(req)

    # res = res[key]
    res = req["message"]
    next_noun, col = siritori.return_nextnoun(res)

    if col:
        siritori = Siritori("output.txt")
        return Response(response=next_noun, status=201)
    return next_noun

# 実行 http://localhost:5000
if __name__ == "__main__":
    # port番号5000番でデバッグモードON(ファイル変更時に自動でリロードしてくれる)
    app.run(port=5000, debug=True, host="0.0.0.0")
    # デフォルトはhttp://127.0.0.1 (自分のみアクセスできる)で
    # これを0.0.0.0(誰でもアクセス)にするときはapp.run(port=5000, debug=True, host='0.0.0.0')
    # 例えばEC2にAPIサーバを置く場合は試してみるといいかも
    # jsonifyを使うと辞書型をjson形式に変換でき、
    # response headerをapplication/jsonで返してくれる
    # でも例えば「これは〜です」resを返すだけなら使う必要はなさそう