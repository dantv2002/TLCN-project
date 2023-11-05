import os
import sys

project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
sys.path.insert(0, project_root)


from flask import Flask, request

from src.services.predictService import predictService
from src.models import deseaseModels

app = Flask(__name__)
@app._got_first_request
def run_before_first_request():
    # Instantiate and run your custom class
    my_instance = deseaseModels()
    my_instance.run()

@app.route("/image/predict", methods=["POST"])
def predict():
    try:
        image = request.get_json()["imageURL"]
        print(image)
        # Run model
        testURL = "https://firebasestorage.googleapis.com/v0/b/practicefirebase-f0570.appspot.com/o/images%2F01.jpeg?alt=media&token=e3fe96f2-18be-4f05-befc-98a8dcf1c354"
        predictSv = predictService(testURL)
        result = predictSv.predict()
        # Return result
        return result, 200
    except:
        return "Error", 500


if __name__ == "__main__":
    app.run(debug=True)
