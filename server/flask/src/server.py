from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route("/image/predict", methods=["POST"])
def predict():
    image = request.get_json()["image"]
    #
    result = "nomal"
    #
    data = {
        "image": image,
        "result": result
    }
    return jsonify(data)


if __name__ == "__main__":
    app.run(debug=True)
