<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <!-- Main content ends here -->

    <footer class="footer">
        <div class="mandala-divider"></div>

        <div class="footer-main">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 mb-4 mb-lg-0">
                        <div class="footer-brand">
                            <h2>Digital-<span>तीर्थ</span></h2>
                            <p>A Crowdsourced "Digital Pilgrimage" Platform for Preserving Nepal's Endangered Heritage Sites</p>
                        </div>
                        <div class="footer-social">
                            <a href="#" class="social-icon"><i class="fab fa-facebook-f"></i></a>
                            <a href="#" class="social-icon"><i class="fab fa-twitter"></i></a>
                            <a href="#" class="social-icon"><i class="fab fa-instagram"></i></a>
                            <a href="#" class="social-icon"><i class="fab fa-youtube"></i></a>
                        </div>
                    </div>

                    <div class="col-lg-2 col-md-4 mb-4 mb-md-0">
                        <h5 class="footer-heading">Explore</h5>
                        <ul class="footer-links">
                            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/sites">Heritage Sites</a></li>
                            <li><a href="${pageContext.request.contextPath}/media">Media</a></li>
                            <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
                        </ul>
                    </div>

                    <div class="col-lg-2 col-md-4 mb-4 mb-md-0">
                        <h5 class="footer-heading">Contribute</h5>
                        <ul class="footer-links">
                            <li><a href="${pageContext.request.contextPath}/sites">Visit a Site</a></li>
                            <li><a href="${pageContext.request.contextPath}/media/upload">Upload Media</a></li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>

        <div class="footer-bottom">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-6">
                        <p class="mb-md-0">&copy; 202 Digital-तीर्थ. All rights reserved.</p>
                    </div>
                    <div class="col-md-6 text-md-end">
                        <ul class="footer-bottom-links">
<%--                            <li><a href="${pageContext.request.contextPath}/privacy">Privacy Policy</a></li>--%>
<%--                            <li><a href="${pageContext.request.contextPath}/terms">Terms of Service</a></li>--%>
                            <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
                            <li><a href="https://www.abhishek-sharma.com.np/" target="_blank">Developer: Abhishek Sharma</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom JS -->
    <script>
        // Add any custom JavaScript here
    </script>
</body>
</html>

<style>
    .footer {
        background-color: #fff;
        color: #333;
        margin-top: 50px;
    }

    .footer-main {
        padding: 50px 0;
        background-color: #f8f9fa;
        background-image: url('https://www.transparenttextures.com/patterns/batik.png');
    }

    .footer-brand h2 {
        font-family: 'Yatra One', cursive;
        font-size: 2rem;
        color: #5c2d91;
        margin-bottom: 15px;
    }

    .footer-brand span {
        color: #ff9e1b;
    }

    .footer-brand p {
        color: #666;
        margin-bottom: 20px;
    }

    .footer-social {
        display: flex;
        gap: 15px;
        margin-bottom: 20px;
    }

    .social-icon {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background-color: #5c2d91;
        color: white;
        display: flex;
        justify-content: center;
        align-items: center;
        transition: all 0.3s;
    }

    .social-icon:hover {
        background-color: #4a2276;
        transform: translateY(-3px);
    }

    .footer-heading {
        font-size: 1.2rem;
        font-weight: 600;
        color: #5c2d91;
        margin-bottom: 20px;
        position: relative;
        padding-bottom: 10px;
    }

    .footer-heading::after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        width: 50px;
        height: 2px;
        background-color: #ff9e1b;
    }

    .footer-links {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .footer-links li {
        margin-bottom: 10px;
    }

    .footer-links a {
        color: #666;
        text-decoration: none;
        transition: all 0.3s;
    }

    .footer-links a:hover {
        color: #5c2d91;
        padding-left: 5px;
    }

    .footer-artwork {
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }

    .artwork-credit {
        font-size: 0.8rem;
        color: #666;
        margin-top: 10px;
        text-align: center;
    }

    .artwork-credit a {
        color: #5c2d91;
        text-decoration: none;
    }

    .footer-bottom {
        background-color: #5c2d91;
        color: white;
        padding: 20px 0;
    }

    .footer-bottom p {
        margin: 0;
    }

    .footer-bottom-links {
        list-style: none;
        padding: 0;
        margin: 0;
        display: flex;
        justify-content: flex-end;
        gap: 20px;
    }

    .footer-bottom-links li {
        position: relative;
    }

    .footer-bottom-links li:not(:last-child)::after {
        content: '|';
        position: absolute;
        right: -12px;
        color: rgba(255, 255, 255, 0.5);
    }

    .footer-bottom-links a {
        color: white;
        text-decoration: none;
        transition: all 0.3s;
    }

    .footer-bottom-links a:hover {
        color: rgba(255, 255, 255, 0.8);
    }

    @media (max-width: 767px) {
        .footer-bottom-links {
            justify-content: center;
            margin-top: 15px;
        }

        .footer-bottom .text-md-end {
            text-align: center !important;
        }

        .footer-bottom .mb-md-0 {
            margin-bottom: 15px !important;
            text-align: center;
        }
    }
</style>
