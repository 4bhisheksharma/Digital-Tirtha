<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Media - ${site.name} - Digital-तीर्थ</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Poppins', sans-serif;
            color: #333;
        }

        .upload-container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .upload-header {
            background-color: #5c2d91;
            color: white;
            padding: 20px 30px;
        }

        .upload-header h1 {
            font-size: 1.8rem;
            margin-bottom: 5px;
        }

        .upload-header p {
            margin-bottom: 0;
            opacity: 0.8;
        }

        .upload-content {
            padding: 30px;
        }

        .form-label {
            font-weight: 500;
        }

        .form-control, .form-select {
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-bottom: 15px;
        }

        .btn-upload {
            background-color: #5c2d91;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 12px 24px;
            font-weight: 600;
            transition: background-color 0.3s;
        }

        .btn-upload:hover {
            background-color: #4a2276;
        }

        .btn-cancel {
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 12px 24px;
            font-weight: 600;
            transition: background-color 0.3s;
        }

        .btn-cancel:hover {
            background-color: #5a6268;
        }

        .alert {
            margin-bottom: 20px;
        }

        .file-upload {
            position: relative;
            display: inline-block;
            width: 100%;
        }

        .file-upload-label {
            display: block;
            padding: 30px;
            background-color: #f8f9fa;
            border: 2px dashed #ddd;
            border-radius: 5px;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s;
        }

        .file-upload-label:hover {
            background-color: #e9ecef;
            border-color: #adb5bd;
        }

        .file-upload-icon {
            font-size: 2rem;
            color: #6c757d;
            margin-bottom: 10px;
        }

        .file-upload-text {
            font-size: 1rem;
            color: #6c757d;
        }

        .file-upload-input {
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
            cursor: pointer;
        }

        .file-name {
            margin-top: 10px;
            font-size: 0.9rem;
            color: #5c2d91;
            font-weight: 500;
            display: none;
        }

        .site-info {
            background-color: #f0f7ed;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .site-info h3 {
            font-size: 1.2rem;
            margin-bottom: 10px;
            color: #333;
        }

        .site-info p {
            margin-bottom: 0;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="upload-container">
            <div class="upload-header">
                <h1>Upload Media for ${site.name}</h1>
                <p>Share your photos and videos to help document and preserve this heritage site</p>
            </div>

            <div class="upload-content">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>

                <div class="site-info">
                    <h3>About ${site.name}</h3>
                    <p>${site.description}</p>
                </div>

                <form action="${pageContext.request.contextPath}/media/upload" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="siteId" value="${site.id}">

                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="mediaType" class="form-label">Media Type</label>
                                <select class="form-select" id="mediaType" name="mediaType" required>
                                    <option value="">Select media type</option>
                                    <option value="Photo">Photo</option>
                                    <option value="Video">Video</option>
                                    <option value="3D Scan">3D Scan</option>
                                    <option value="Audio">Audio</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="location" class="form-label">Location (optional)</label>
                                <input type="text" class="form-control" id="location" name="location" placeholder="e.g., North entrance, Main hall">
                            </div>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label">Media File</label>
                        <div class="file-upload">
                            <label class="file-upload-label" for="file">
                                <div class="file-upload-icon">
                                    <i class="fas fa-cloud-upload-alt"></i>
                                </div>
                                <div class="file-upload-text">
                                    Drag and drop your file here or click to browse
                                </div>
                            </label>
                            <input type="file" class="file-upload-input" id="file" name="file" accept="image/*,video/*" required>
                        </div>
                        <div class="file-name" id="fileName"></div>
                    </div>

                    <div class="mb-3">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="damageDetection" name="damageDetection">
                            <label class="form-check-label" for="damageDetection">
                                Enable AI damage detection for this media
                            </label>
                        </div>
                        <small class="text-muted">Our AI system will analyze your media to detect signs of damage or deterioration.</small>
                    </div>

                    <div class="d-flex justify-content-between mt-4">
                        <a href="${pageContext.request.contextPath}/sites/${site.id}" class="btn btn-cancel">Cancel</a>
                        <button type="submit" class="btn btn-upload">Upload Media</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom JS -->
    <script>
        document.getElementById('file').addEventListener('change', function(e) {
            var fileName = e.target.files[0].name;
            document.getElementById('fileName').textContent = 'Selected file: ' + fileName;
            document.getElementById('fileName').style.display = 'block';
        });
    </script>
</body>
</html>
