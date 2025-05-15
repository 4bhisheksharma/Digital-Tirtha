<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile - Digital-तीर्थ</title>
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

        .form-label {
            font-weight: 500;
        }

        .form-control {
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-bottom: 15px;
        }

        .btn-update {
            background-color: #5c2d91;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 12px 24px;
            font-weight: 600;
            transition: background-color 0.3s;
        }

        .btn-update:hover {
            background-color: #4a2276;
        }

        .alert {
            margin-bottom: 20px;
        }

        .activity-item {
            padding: 15px;
            border-bottom: 1px solid #eee;
        }

        .activity-item:last-child {
            border-bottom: none;
        }

        .activity-icon {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: #e9ecef;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-right: 15px;
        }

        .activity-icon.visit {
            background-color: #d1e7dd;
            color: #0f5132;
        }

        .activity-icon.donation {
            background-color: #f8d7da;
            color: #842029;
        }

        .activity-icon.media {
            background-color: #cff4fc;
            color: #055160;
        }

        .badge-karma {
            background-color: #5c2d91;
            color: white;
            padding: 5px 10px;
            border-radius: 20px;
            font-weight: 500;
        }
    </style>
</head>
<body>
    <div class="container">
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
                        <button class="nav-link active" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="true">Profile</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="activity-tab" data-bs-toggle="tab" data-bs-target="#activity" type="button" role="tab" aria-controls="activity" aria-selected="false">Recent Activity</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="contributions-tab" data-bs-toggle="tab" data-bs-target="#contributions" type="button" role="tab" aria-controls="contributions" aria-selected="false">My Contributions</button>
                    </li>
                </ul>

                <div class="tab-content" id="profileTabsContent">
                    <div class="tab-pane fade show active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                ${error}
                            </div>
                        </c:if>

                        <c:if test="${not empty success}">
                            <div class="alert alert-success" role="alert">
                                ${success}
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/profile" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="username" class="form-label">Username</label>
                                        <input type="text" class="form-control" id="username" value="${user.username}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="fullName" class="form-label">Full Name</label>
                                        <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}" required>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                            </div>

                            <div class="mb-3">
                                <label for="country" class="form-label">Country</label>
                                <select class="form-select" id="country" name="country">
                                    <option value="">Select your country</option>
                                    <option value="Nepal" ${user.country == 'Nepal' ? 'selected' : ''}>Nepal</option>
                                    <option value="India" ${user.country == 'India' ? 'selected' : ''}>India</option>
                                    <option value="United States" ${user.country == 'United States' ? 'selected' : ''}>United States</option>
                                    <option value="United Kingdom" ${user.country == 'United Kingdom' ? 'selected' : ''}>United Kingdom</option>
                                    <option value="Australia" ${user.country == 'Australia' ? 'selected' : ''}>Australia</option>
                                    <option value="Canada" ${user.country == 'Canada' ? 'selected' : ''}>Canada</option>
                                    <option value="Germany" ${user.country == 'Germany' ? 'selected' : ''}>Germany</option>
                                    <option value="France" ${user.country == 'France' ? 'selected' : ''}>France</option>
                                    <option value="Japan" ${user.country == 'Japan' ? 'selected' : ''}>Japan</option>
                                    <option value="China" ${user.country == 'China' ? 'selected' : ''}>China</option>
                                    <option value="Other" ${user.country == 'Other' ? 'selected' : ''}>Other</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Registration Date</label>
                                <input type="text" class="form-control" value="${user.registrationDate}" readonly>
                            </div>

                            <button type="submit" class="btn btn-update">Update Profile</button>
                        </form>
                    </div>

                    <div class="tab-pane fade" id="activity" role="tabpanel" aria-labelledby="activity-tab">
                        <h3 class="mb-4">Recent Activity</h3>

                        <div class="activity-list">
                            <!-- Sample activities - in a real app, these would be dynamically generated -->
                            <div class="activity-item d-flex align-items-center">
                                <div class="activity-icon visit">
                                    <i class="fas fa-map-marker-alt"></i>
                                </div>
                                <div>
                                    <p class="mb-0"><strong>You visited Swayambhunath Stupa</strong></p>
                                    <small class="text-muted">2 days ago</small>
                                </div>
                            </div>

                            <div class="activity-item d-flex align-items-center">
                                <div class="activity-icon donation">
                                    <i class="fas fa-hand-holding-heart"></i>
                                </div>
                                <div>
                                    <p class="mb-0"><strong>You donated $25 to Patan Durbar Square</strong></p>
                                    <small class="text-muted">1 week ago</small>
                                </div>
                            </div>

                            <div class="activity-item d-flex align-items-center">
                                <div class="activity-icon media">
                                    <i class="fas fa-camera"></i>
                                </div>
                                <div>
                                    <p class="mb-0"><strong>You contributed a photo to Boudhanath Stupa</strong></p>
                                    <small class="text-muted">2 weeks ago</small>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="contributions" role="tabpanel" aria-labelledby="contributions-tab">
                        <h3 class="mb-4">My Contributions</h3>

                        <div class="row">
                            <div class="col-md-6 mb-4">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Media Contributions</h5>
                                        <p class="card-text">You have contributed ${user.mediaContributed} photos and videos to heritage sites.</p>
                                        <a href="#" class="btn btn-sm btn-primary">View All</a>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 mb-4">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Donations</h5>
                                        <p class="card-text">You have donated a total of $${user.totalDonations} to heritage sites.</p>
                                        <a href="#" class="btn btn-sm btn-primary">View All</a>
                                    </div>
                                </div>
                            </div>
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
