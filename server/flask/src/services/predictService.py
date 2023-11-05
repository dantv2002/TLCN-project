import numpy as np
from src.models.deseaseModels import deseaseModels
from tensorflow.keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array
from tensorflow.keras.utils import get_file

class predictService:
    def __init__(self, url):
        """
        Create a new predict service.

        Args:
            url: The image URL.
        """
        self.imageURL = url
        self.result = None
        self.models = deseaseModels()
        
    def predict(self):
        """
        predict.

        Returns:
            The result.
        """
        predictions = self.models.weightModel.predict(self.preprocessImage())
        predicted_class = np.argmax(predictions)
        self.result = self.models.labels[predicted_class]["name"]
        return self.result
    
    def preprocessImage(self):
        file_name = 'predict_image'
        image = load_img(get_file(file_name, self.imageURL), target_size=(200, 200), color_mode = "grayscale")
        image_array = img_to_array(image)
        image_array = np.expand_dims(image_array, axis=0)
        image_array = image_array / 255.0
        return image_array