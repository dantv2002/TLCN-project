import tensorflow as tf
import csv
import os
import gdown

class deseaseModels:
    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(deseaseModels, cls).__new__(cls)
            cls._instance.init_singleton()
        return cls._instance

    def init_singleton(self):
        # Initialization code for the singleton instance
        # Use the default parameters here
        try:
            file_id = '1XaP_oF_j8jeAW8r0aDtYi-9DNZ3htAKX'
            url = f'https://drive.google.com/uc?id={file_id}'
            path = '../datas/'
            file_name = 'models/model_weights.h5'
            if not os.path.exists(path+file_name):
        # File does not exist, so download it
                gdown.download(url, path+file_name, quiet=False)
            self.weightModel = tf.keras.models.load_model(path+file_name)
            self.labels = []
            with open(path + 'images/names.csv', newline='') as f:
                reader = csv.DictReader(f)
                for row in reader:
                    self.labels.append(row['name'])
                    print(row['name'])
        except:
            self.weightModel = None
            self.labels = None
            
