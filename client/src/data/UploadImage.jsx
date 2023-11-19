import React, { useEffect, useState } from 'react';
import { imageDb } from './Config';
import { getDownloadURL, ref, uploadBytes } from 'firebase/storage';
import { v4 as uuidv4 } from 'uuid';
import "../css/Admin.css"

const UploadImage = ({ onImageUpload }) => {
    const [img, setImg] = useState(null);
    const [imgUrl, setImgUrl] = useState(null);

    const handleUpload = () => {

        if (!img) return;
        const imgRef = ref(imageDb, `files/${uuidv4()}`);
        uploadBytes(imgRef, img).then(() => {
            getDownloadURL(imgRef).then((url) => {
                setImgUrl(url);
                onImageUpload(url);
            });
        });
    };

    useEffect(() => {

        if (imgUrl) return;

        const latestImgRef = ref(imageDb, 'files');
        getDownloadURL(latestImgRef)
        .then((url) => {
            setImgUrl(url);
        })
        .catch((error) => {
            console.error('Error getting latest image:', error);
        });
    }, [imgUrl]);

    return (
        <div className='upload_image'>
        <input type='file' onChange={(e) => setImg(e.target.files[0])} />
        <button onClick={handleUpload}>Tải ảnh lên</button>
        <br />
        {imgUrl && <img src={imgUrl} height='370px' width='370px' alt='Latest Uploaded Image' />}
        </div>
  );
};

export default UploadImage;
