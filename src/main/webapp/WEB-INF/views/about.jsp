<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="About Us" />
    <jsp:param name="activeNav" value="about" />
    <jsp:param name="customCss" value="
        <style>
            .about-header {
                background-color: var(--primary-color);
                background-image: linear-gradient(rgba(92, 45, 145, 0.8), rgba(92, 45, 145, 0.9)), url('https://images.unsplash.com/photo-1605640840605-14ac1855827b');
                background-size: cover;
                background-position: center;
                color: white;
                padding: 80px 0;
                text-align: center;
                position: relative;
                overflow: hidden;
            }

            .about-header::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-image: url('https://www.transparenttextures.com/patterns/batik.png');
                opacity: 0.1;
            }

            .about-header h1 {
                font-size: 3rem;
                margin-bottom: 20px;
                position: relative;
            }

            .about-header p {
                font-size: 1.2rem;
                max-width: 800px;
                margin: 0 auto;
                position: relative;
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

            .mission-section {
                padding: 80px 0;
                background-color: #f8f9fa;
                background-image: url('https://www.transparenttextures.com/patterns/batik.png');
            }

            .mission-card {
                background-color: white;
                border-radius: 15px;
                overflow: hidden;
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
                padding: 40px;
                text-align: center;
                margin-bottom: 30px;
            }

            .mission-icon {
                font-size: 3rem;
                color: var(--primary-color);
                margin-bottom: 20px;
            }

            .mission-card h3 {
                font-size: 1.8rem;
                color: var(--primary-color);
                margin-bottom: 20px;
            }

            .mission-card p {
                color: #666;
                line-height: 1.7;
            }

            .team-section {
                padding: 80px 0;
            }

            .team-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 30px;
                margin-top: 50px;
            }

            .team-member {
                background-color: white;
                border-radius: 15px;
                overflow: hidden;
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s, box-shadow 0.3s;
            }

            .team-member:hover {
                transform: translateY(-10px);
                box-shadow: 0 15px 40px rgba(92, 45, 145, 0.2);
            }

            .team-photo {
                height: 250px;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                position: relative;
            }

            .team-photo img {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            .team-photo-placeholder {
                width: 100%;
                height: 100%;
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #e9ecef;
                color: var(--primary-color);
                font-size: 4rem;
            }

            .team-info {
                padding: 20px;
                text-align: center;
            }

            .team-info h3 {
                font-size: 1.3rem;
                color: var(--primary-color);
                margin-bottom: 5px;
            }

            .team-info p {
                color: #666;
                margin-bottom: 15px;
                font-size: 0.9rem;
            }

            .team-social {
                display: flex;
                justify-content: center;
                gap: 10px;
            }

            .team-social a {
                width: 30px;
                height: 30px;
                border-radius: 50%;
                background-color: #f8f9fa;
                color: var(--primary-color);
                display: flex;
                justify-content: center;
                align-items: center;
                transition: all 0.3s;
                font-size: 0.8rem;
            }

            .team-social a:hover {
                background-color: var(--primary-color);
                color: white;
            }

            .timeline-section {
                padding: 80px 0;
                background-color: #f8f9fa;
                background-image: url('https://www.transparenttextures.com/patterns/batik.png');
            }

            .timeline {
                position: relative;
                max-width: 800px;
                margin: 0 auto;
                padding: 40px 0;
            }

            .timeline::before {
                content: '';
                position: absolute;
                top: 0;
                bottom: 0;
                left: 50%;
                width: 3px;
                background-color: var(--primary-color);
                transform: translateX(-50%);
            }

            .timeline-item {
                position: relative;
                margin-bottom: 50px;
            }

            .timeline-item:last-child {
                margin-bottom: 0;
            }

            .timeline-content {
                position: relative;
                width: calc(50% - 40px);
                padding: 20px;
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .timeline-item:nth-child(odd) .timeline-content {
                margin-left: auto;
            }

            .timeline-item:nth-child(even) .timeline-content {
                margin-right: auto;
            }

            .timeline-content::before {
                content: '';
                position: absolute;
                top: 20px;
                width: 20px;
                height: 20px;
                background-color: white;
                transform: rotate(45deg);
            }

            .timeline-item:nth-child(odd) .timeline-content::before {
                left: -10px;
            }

            .timeline-item:nth-child(even) .timeline-content::before {
                right: -10px;
            }

            .timeline-date {
                position: absolute;
                top: 20px;
                width: 80px;
                padding: 5px 10px;
                background-color: var(--primary-color);
                color: white;
                border-radius: 20px;
                text-align: center;
                font-size: 0.9rem;
                font-weight: 500;
            }

            .timeline-item:nth-child(odd) .timeline-date {
                left: -100px;
            }

            .timeline-item:nth-child(even) .timeline-date {
                right: -100px;
            }

            .timeline-dot {
                position: absolute;
                top: 20px;
                left: 50%;
                width: 20px;
                height: 20px;
                background-color: var(--secondary-color);
                border-radius: 50%;
                transform: translateX(-50%);
                z-index: 1;
            }

            .timeline-content h3 {
                font-size: 1.3rem;
                color: var(--primary-color);
                margin-bottom: 10px;
            }

            .timeline-content p {
                color: #666;
                line-height: 1.6;
                margin-bottom: 0;
            }

            @media (max-width: 768px) {
                .timeline::before {
                    left: 30px;
                }

                .timeline-content {
                    width: calc(100% - 80px);
                    margin-left: 80px !important;
                }

                .timeline-item:nth-child(odd) .timeline-content::before,
                .timeline-item:nth-child(even) .timeline-content::before {
                    left: -10px;
                }

                .timeline-date {
                    width: 60px;
                    font-size: 0.8rem;
                }

                .timeline-item:nth-child(odd) .timeline-date,
                .timeline-item:nth-child(even) .timeline-date {
                    left: -70px;
                }

                .timeline-dot {
                    left: 30px;
                }
            }
        </style>
    " />
</jsp:include>

<div class="main-content">
    <div class="about-header">
        <div class="container">
            <h1>About Digital-तीर्थ</h1>
            <p>Learn about our mission to preserve Nepal's endangered heritage sites through digital technology and community engagement</p>
        </div>
    </div>

    <div class="mission-section">
        <div class="container">
            <div class="section-title">
                <h2>Our Mission</h2>
                <p>We're dedicated to preserving Nepal's cultural heritage for future generations</p>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="mission-card">
                        <div class="mission-icon">
                            <i class="fas fa-eye"></i>
                        </div>
                        <h3>Vision</h3>
                        <p>To create a world where Nepal's cultural heritage is preserved, celebrated, and accessible to all through digital technology.</p>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="mission-card">
                        <div class="mission-icon">
                            <i class="fas fa-bullseye"></i>
                        </div>
                        <h3>Mission</h3>
                        <p>To document, preserve, and restore Nepal's endangered heritage sites through crowdsourced contributions and community engagement.</p>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="mission-card">
                        <div class="mission-icon">
                            <i class="fas fa-heart"></i>
                        </div>
                        <h3>Values</h3>
                        <p>Cultural preservation, community empowerment, technological innovation, transparency, and inclusivity guide everything we do.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="team-section">
        <div class="container">
            <div class="section-title">
                <h2>Our Team</h2>
                <p>Meet the passionate individuals behind Digital-तीर्थ</p>
            </div>

            <div class="team-grid">
                <div class="team-member">
                    <div class="team-photo">
                        <img src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80" alt="Aarav Sharma">
                    </div>
                    <div class="team-info">
                        <h3>Aarav Sharma</h3>
                        <p>Founder & CEO</p>
                        <div class="team-social">
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-linkedin-in"></i></a>
                            <a href="#"><i class="fab fa-github"></i></a>
                        </div>
                    </div>
                </div>

                <div class="team-member">
                    <div class="team-photo">
                        <img src="https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=776&q=80" alt="Priya Patel">
                    </div>
                    <div class="team-info">
                        <h3>Priya Patel</h3>
                        <p>Chief Technology Officer</p>
                        <div class="team-social">
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-linkedin-in"></i></a>
                            <a href="#"><i class="fab fa-github"></i></a>
                        </div>
                    </div>
                </div>

                <div class="team-member">
                    <div class="team-photo">
                        <img src="https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80" alt="Raj Gurung">
                    </div>
                    <div class="team-info">
                        <h3>Raj Gurung</h3>
                        <p>Head of Preservation</p>
                        <div class="team-social">
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-linkedin-in"></i></a>
                            <a href="#"><i class="fab fa-github"></i></a>
                        </div>
                    </div>
                </div>

                <div class="team-member">
                    <div class="team-photo">
                        <img src="https://images.unsplash.com/photo-1580489944761-15a19d654956?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=922&q=80" alt="Maya Thapa">
                    </div>
                    <div class="team-info">
                        <h3>Maya Thapa</h3>
                        <p>Community Manager</p>
                        <div class="team-social">
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-linkedin-in"></i></a>
                            <a href="#"><i class="fab fa-github"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="timeline-section">
        <div class="container">
            <div class="section-title">
                <h2>Our Journey</h2>
                <p>The story of Digital-तीर्थ from concept to reality</p>
            </div>

            <div class="timeline">
                <div class="timeline-item">
                    <div class="timeline-dot"></div>
                    <div class="timeline-date">2020</div>
                    <div class="timeline-content">
                        <h3>The Beginning</h3>
                        <p>Digital-तीर्थ was conceived after the founder witnessed the deterioration of several heritage sites in Nepal following the 2015 earthquake.</p>
                    </div>
                </div>

                <div class="timeline-item">
                    <div class="timeline-dot"></div>
                    <div class="timeline-date">2021</div>
                    <div class="timeline-content">
                        <h3>Platform Launch</h3>
                        <p>The first version of Digital-तीर्थ was launched, focusing on documenting and mapping endangered heritage sites across Nepal.</p>
                    </div>
                </div>

                <div class="timeline-item">
                    <div class="timeline-dot"></div>
                    <div class="timeline-date">2022</div>
                    <div class="timeline-content">
                        <h3>Community Growth</h3>
                        <p>Our community grew to over 1,000 members, contributing photos, videos, and stories about heritage sites throughout Nepal.</p>
                    </div>
                </div>

                <div class="timeline-item">
                    <div class="timeline-dot"></div>
                    <div class="timeline-date">2023</div>
                    <div class="timeline-content">
                        <h3>Today</h3>
                        <p>Digital-तीर्थ has documented over 150 heritage sites, raised funds for restoration projects, and continues to grow its impact across Nepal.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
