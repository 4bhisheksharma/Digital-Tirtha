<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="Nepal's Heritage Preservation Platform" />
    <jsp:param name="activeNav" value="home" />
    <jsp:param name="customCss" value="
        <style>
            .hero {
                background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://images.unsplash.com/photo-1605640840605-14ac1855827b');
                background-size: cover;
                background-position: center;
                height: 600px;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                text-align: center;
                color: white;
                padding: 0 20px;
                position: relative;
            }

            .hero::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-image: url('https://www.transparenttextures.com/patterns/batik.png');
                opacity: 0.2;
            }

            .hero-content {
                position: relative;
                z-index: 1;
                max-width: 800px;
            }

            .hero h1 {
                font-family: 'Yatra One', cursive;
                font-size: 4.5rem;
                margin-bottom: 20px;
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
            }

            .hero p {
                font-size: 1.5rem;
                margin-bottom: 30px;
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
            }

            .btn-hero {
                display: inline-block;
                padding: 15px 30px;
                background-color: var(--primary-color);
                color: white;
                text-decoration: none;
                border-radius: 50px;
                font-size: 1.1rem;
                font-weight: bold;
                transition: all 0.3s;
                border: 2px solid transparent;
            }

            .btn-hero:hover {
                background-color: transparent;
                border-color: white;
                transform: translateY(-3px);
            }

            .section-title {
                text-align: center;
                margin-bottom: 60px;
                position: relative;
            }

            .section-title h2 {
                font-size: 2.5rem;
                color: var(--primary-color);
                margin-bottom: 15px;
                font-weight: 600;
            }

            .section-title p {
                font-size: 1.2rem;
                color: #666;
                max-width: 800px;
                margin: 0 auto;
            }

            .section-title::after {
                content: '';
                position: absolute;
                bottom: -20px;
                left: 50%;
                transform: translateX(-50%);
                width: 80px;
                height: 3px;
                background-color: var(--secondary-color);
            }

            .features {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
                gap: 30px;
                margin-top: 50px;
            }

            .feature-card {
                background-color: white;
                border-radius: 15px;
                overflow: hidden;
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s, box-shadow 0.3s;
                border: 1px solid rgba(0, 0, 0, 0.05);
            }

            .feature-card:hover {
                transform: translateY(-10px);
                box-shadow: 0 15px 40px rgba(92, 45, 145, 0.2);
            }

            .feature-img {
                height: 200px;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 1.5rem;
                color: var(--primary-color);
                position: relative;
                overflow: hidden;
            }

            .feature-img::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-image: url('https://www.transparenttextures.com/patterns/batik.png');
                opacity: 0.1;
            }

            .feature-img i {
                font-size: 4rem;
                z-index: 1;
            }

            .feature-content {
                padding: 30px;
            }

            .feature-content h3 {
                font-size: 1.5rem;
                margin-top: 0;
                margin-bottom: 15px;
                color: var(--primary-color);
                font-weight: 600;
            }

            .feature-content p {
                color: #666;
                margin-bottom: 25px;
                line-height: 1.7;
            }

            .feature-btn {
                display: inline-block;
                padding: 10px 20px;
                background-color: var(--primary-color);
                color: white;
                text-decoration: none;
                border-radius: 50px;
                font-size: 0.9rem;
                font-weight: 500;
                transition: all 0.3s;
            }

            .feature-btn:hover {
                background-color: var(--primary-dark);
                transform: translateY(-2px);
            }

            .cta-section {
                background-color: #f8f9fa;
                background-image: url('https://www.transparenttextures.com/patterns/batik.png');
                padding: 80px 20px;
                text-align: center;
                margin-top: 80px;
                border-radius: 15px;
            }

            .cta-section h2 {
                font-size: 2.5rem;
                margin-bottom: 20px;
                color: var(--primary-color);
                font-weight: 600;
            }

            .cta-section p {
                font-size: 1.2rem;
                color: #666;
                max-width: 800px;
                margin: 0 auto 30px;
            }

            .stats-section {
                padding: 80px 0;
                background-color: white;
            }

            .stats-container {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                gap: 30px;
            }

            .stat-item {
                text-align: center;
                padding: 30px;
                border-radius: 10px;
                background-color: #f8f9fa;
                transition: all 0.3s;
            }

            .stat-item:hover {
                transform: translateY(-5px);
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            }

            .stat-icon {
                font-size: 2.5rem;
                color: var(--primary-color);
                margin-bottom: 15px;
            }

            .stat-value {
                font-size: 2.5rem;
                font-weight: 700;
                color: var(--primary-color);
                margin-bottom: 10px;
            }

            .stat-label {
                font-size: 1rem;
                color: #666;
            }

            @media (max-width: 768px) {
                .hero h1 {
                    font-size: 3rem;
                }

                .hero p {
                    font-size: 1.2rem;
                }

                .section-title h2 {
                    font-size: 2rem;
                }

                .cta-section h2 {
                    font-size: 2rem;
                }
            }
        </style>
    " />
</jsp:include>

<div class="main-content">
    <div class="hero">
        <div class="hero-content">
            <h1>Digital-तीर्थ</h1>
            <p>A Crowdsourced "Digital Pilgrimage" Platform for Preserving Nepal's Endangered Heritage Sites</p>
            <a href="${pageContext.request.contextPath}/sites" class="btn-hero">Explore Heritage Sites</a>
        </div>
    </div>

    <div class="container">
        <div class="section-title">
            <h2>Preserving Nepal's Cultural Legacy</h2>
            <p>Digital-तीर्थ connects people worldwide with Nepal's endangered heritage sites through virtual pilgrimages, crowdsourced documentation, and community-driven funding.</p>
        </div>

        <div class="features">
            <div class="feature-card">
                <div class="feature-img">
                    <i class="fas fa-vr-cardboard"></i>
                </div>
                <div class="feature-content">
                    <h3>Virtual Pilgrimage Engine</h3>
                    <p>Explore sites via 360° tours and narrated stories in Nepali/English. Experience AI-generated reconstructions showing how sites could look after restoration.</p>
                    <a href="${pageContext.request.contextPath}/sites" class="feature-btn">Start Your Journey</a>
                </div>
            </div>

            <div class="feature-card">
                <div class="feature-img">
                    <i class="fas fa-camera-retro"></i>
                </div>
                <div class="feature-content">
                    <h3>Crowdsourced Heritage Mapping</h3>
                    <p>Locals and tourists upload photos/videos of at-risk sites. AI damage detection identifies cracks, vegetation overgrowth, or structural issues in uploaded media.</p>
                    <a href="${pageContext.request.contextPath}/media/upload" class="feature-btn">Contribute Media</a>
                </div>
            </div>

            <div class="feature-card">
                <div class="feature-img">
                    <i class="fas fa-hand-holding-heart"></i>
                </div>
                <div class="feature-content">
                    <h3>Community-Driven Funding</h3>
                    <p>Donate directly to specific sites or "adopt a monument." Transparent ledger shows how funds are used for preservation and restoration efforts.</p>
                    <a href="${pageContext.request.contextPath}/sites" class="feature-btn">Support a Site</a>
                </div>
            </div>
        </div>
    </div>

    <div class="container stats-section">
        <div class="section-title">
            <h2>Our Impact</h2>
            <p>Together, we're making a difference in preserving Nepal's cultural heritage</p>
        </div>

        <div class="stats-container">
            <div class="stat-item">
                <div class="stat-icon">
                    <i class="fas fa-landmark"></i>
                </div>
                <div class="stat-value">150+</div>
                <div class="stat-label">Heritage Sites Documented</div>
            </div>

            <div class="stat-item">
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="stat-value">5,000+</div>
                <div class="stat-label">Community Members</div>
            </div>

            <div class="stat-item">
                <div class="stat-icon">
                    <i class="fas fa-images"></i>
                </div>
                <div class="stat-value">25,000+</div>
                <div class="stat-label">Media Contributions</div>
            </div>

            <div class="stat-item">
                <div class="stat-icon">
                    <i class="fas fa-dollar-sign"></i>
                </div>
                <div class="stat-value">$120K+</div>
                <div class="stat-label">Funds Raised</div>
            </div>
        </div>
    </div>

    <div class="cta-section">
        <div class="container">
            <h2>Join the Movement</h2>
            <p>Help preserve Nepal's cultural heritage for future generations. Start your digital pilgrimage today and earn karma points for your contributions.</p>
            <a href="${pageContext.request.contextPath}/signup" class="btn-hero">Sign Up Now</a>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
