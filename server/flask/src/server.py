from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route("/image/predict", methods=["POST"])
def predict():
    image = request.get_json()["image"]
    print(image)
    # Run model
    result = "nomal"
    # Return result
    return result, 500


if __name__ == "__main__":
    app.run(debug=True, port=8081)
