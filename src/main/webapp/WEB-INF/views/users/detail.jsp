<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${user.fullName} - Digital-तीर्थ</title>
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

        .profile-container {
            max-width: 1000px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .profile-header {
            background-color: #5c2d91;
            color: white;
            padding: 30px;
            text-align: center;
        }

        .profile-header h1 {
            font-size: 2.5rem;
            margin-bottom: 10px;
        }

        .profile-avatar {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            background-color: #fff;
            color: #5c2d91;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 3rem;
            margin: 0 auto 20px;
            border: 5px solid rgba(255, 255, 255, 0.3);
        }

        .profile-stats {
            display: flex;
            justify-content: center;
            gap: 30px;
            margin-top: 20px;
        }

        .stat-item {
            text-align: center;
        }

        .stat-value {
            font-size: 1.8rem;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .stat-label {
            font-size: 0.9rem;
            opacity: 0.8;
        }

        .profile-content {
            padding: 30px;
        }

        .nav-tabs {
            border-bottom: 1px solid #ddd;
            margin-bottom: 30px;
        }

        .nav-tabs .nav-link {
            color: #666;
            border: none;
            padding: 10px 20px;
            font-weight: 500;
        }

        .nav-tabs .nav-link.active {
            color: #5c2d91;
            border-bottom: 3px solid #5c2d91;
            background-color: transparent;
        }

        .badge-karma {
            background-color: #5c2d91;
            color: white;
            padding: 5px 10px;
            border-radius: 20px;
            font-weight: 500;
        }

        .contribution-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }

        .contribution-item {
            height: 150px;
            background-color: #ddd;
            border-radius: 5px;
            overflow: hidden;
            position: relative;
        }

        .contribution-item img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .contribution-overlay {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            padding: 8px;
            font-size: 0.9rem;
        }

        .btn-back {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/sites" class="btn btn-primary btn-back">
            <i class="fas fa-arrow-left"></i> Back to Sites
        </a>

        <div class="profile-container">
            <div class="profile-header">
                <div class="profile-avatar">
                    <i class="fas fa-user"></i>
                </div>
                <h1>${user.fullName}</h1>
                <p>@${user.username}</p>
                <span class="badge badge-karma">
                    <i class="fas fa-star"></i> ${user.karmaPoints} Karma Points
                </span>

                <div class="profile-stats">
                    <div class="stat-item">
                        <div class="stat-value">${user.sitesVisited}</div>
                        <div class="stat-label">Sites Visited</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat-value">${user.mediaContributed}</div>
                        <div class="stat-label">Media Contributed</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat-value">$${user.totalDonations}</div>
                        <div class="stat-label">Total Donations</div>
                    </div>
                </div>
            </div>

            <div class="profile-content">
                <ul class="nav nav-tabs" id="profileTabs" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="about-tab" data-bs-toggle="tab" data-bs-target="#about" type="button" role="tab" aria-controls="about" aria-selected="true">About</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="contributions-tab" data-bs-toggle="tab" data-bs-target="#contributions" type="button" role="tab" aria-controls="contributions" aria-selected="false">Contributions</button>
                    </li>
                </ul>

                <div class="tab-content" id="profileTabsContent">
                    <div class="tab-pane fade show active" id="about" role="tabpanel" aria-labelledby="about-tab">
                        <h3 class="mb-4">About ${user.fullName}</h3>

                        <div class="row">
                            <div class="col-md-6">
                                <p><strong>Username:</strong> ${user.username}</p>
                                <p><strong>Country:</strong> ${user.country}</p>
                                <p><strong>Local Resident:</strong> ${user.local ? 'Yes' : 'No'}</p>
                            </div>
                            <div class="col-md-6">
                                <p><strong>Member Since:</strong> ${user.registrationDate}</p>
                                <p><strong>Last Active:</strong> ${user.lastLoginDate}</p>
                                <p><strong>Karma Level:</strong> 
                                    <c:choose>
                                        <c:when test="${user.karmaPoints >= 1000}">Guardian</c:when>
                                        <c:when test="${user.karmaPoints >= 500}">Protector</c:when>
                                        <c:when test="${user.karmaPoints >= 200}">Preserver</c:when>
                                        <c:when test="${user.karmaPoints >= 100}">Contributor</c:when>
                                        <c:otherwise>Pilgrim</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>

                        <div class="mt-4">
                            <h4>Contribution Summary</h4>
                            <p>
                                ${user.fullName} has visited ${user.sitesVisited} heritage sites, contributed ${user.mediaContributed} 
                                media items, and donated $${user.totalDonations} to preservation efforts.
                            </p>

                            <c:if test="${user.local}">
                                <div class="alert alert-info mt-3">
                                    <i class="fas fa-info-circle"></i> ${user.fullName} is a local resident of Nepal and contributes valuable local knowledge to the platform.
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="contributions" role="tabpanel" aria-labelledby="contributions-tab">
                        <h3 class="mb-4">Media Contributions</h3>

                        <div class="contribution-grid">
                            <!-- Sample contributions - in a real app, these would be dynamically generated -->
                            <div class="contribution-item">
                                <img src="https://images.unsplash.com/photo-1605640840605-14ac1855827b" alt="Heritage Site">
                                <div class="contribution-overlay">Swayambhunath Stupa</div>
                            </div>
                            <div class="contribution-item">
                                <img src="https://images.unsplash.com/photo-1605640840605-14ac1855827b" alt="Heritage Site">
                                <div class="contribution-overlay">Patan Durbar Square</div>
                            </div>
                            <div class="contribution-item">
                                <img src="https://images.unsplash.com/photo-1605640840605-14ac1855827b" alt="Heritage Site">
                                <div class="contribution-overlay">Boudhanath Stupa</div>
                            </div>
                        </div>

                        <h3 class="mb-4 mt-5">Recent Donations</h3>

                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Heritage Site</th>
                                        <th>Amount</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Sample donations - in a real app, these would be dynamically generated -->
                                    <tr>
                                        <td>Patan Durbar Square</td>
                                        <td>$25.00</td>
                                        <td>2023-06-15</td>
                                    </tr>
                                    <tr>
                                        <td>Swayambhunath Stupa</td>
                                        <td>$15.00</td>
                                        <td>2023-05-22</td>
                                    </tr>
                                    <tr>
                                        <td>Boudhanath Stupa</td>
                                        <td>$30.00</td>
                                        <td>2023-04-10</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
