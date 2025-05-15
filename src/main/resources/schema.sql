-- Digital-Tirtha Database Schema

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- Hashed password
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    country VARCHAR(50),
    is_local BOOLEAN DEFAULT FALSE,
    karma_points INT DEFAULT 0,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login_date TIMESTAMP,
    sites_visited INT DEFAULT 0,
    media_contributed INT DEFAULT 0,
    total_donations DECIMAL(10, 2) DEFAULT 0.0,
    role VARCHAR(20) DEFAULT 'USER' -- USER, ADMIN, MODERATOR
);

-- Heritage Sites Table
CREATE TABLE IF NOT EXISTS heritage_sites (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(100) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    risk_level VARCHAR(20), -- Low, Medium, High, Critical
    category VARCHAR(50), -- Temple, Monastery, UNESCO site, etc.
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    image_url VARCHAR(255),
    virtual_tour_url VARCHAR(255),
    visit_count INT DEFAULT 0,
    donation_amount DECIMAL(10, 2) DEFAULT 0.0
);

-- Media Contributions Table
CREATE TABLE IF NOT EXISTS media_contributions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    site_id BIGINT NOT NULL,
    media_type VARCHAR(20) NOT NULL, -- Photo, Video, 3D Scan, etc.
    file_url VARCHAR(255) NOT NULL,
    thumbnail_url VARCHAR(255),
    title VARCHAR(100),
    description TEXT,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    capture_date TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, APPROVED, REJECTED
    location VARCHAR(100),
    latitude DOUBLE,
    longitude DOUBLE,
    has_damage_detection BOOLEAN DEFAULT FALSE,
    damage_level VARCHAR(20), -- None, Low, Medium, High
    damage_description TEXT,
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (site_id) REFERENCES heritage_sites(id)
);

-- Donations Table
CREATE TABLE IF NOT EXISTS donations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    site_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) DEFAULT 'NPR', -- NPR, USD, etc.
    donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, COMPLETED, FAILED
    payment_method VARCHAR(50), -- eSewa, Khalti, Credit Card, etc.
    transaction_id VARCHAR(100),
    purpose VARCHAR(100), -- General, Specific restoration project, etc.
    is_anonymous BOOLEAN DEFAULT FALSE,
    message TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (site_id) REFERENCES heritage_sites(id)
);

-- Site Visits Table
CREATE TABLE IF NOT EXISTS site_visits (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    site_id BIGINT NOT NULL,
    visit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    visit_type VARCHAR(20) DEFAULT 'VIRTUAL', -- VIRTUAL, PHYSICAL
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (site_id) REFERENCES heritage_sites(id)
);

-- Comments Table
CREATE TABLE IF NOT EXISTS comments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    site_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_approved BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (site_id) REFERENCES heritage_sites(id)
);

-- Events Table
CREATE TABLE IF NOT EXISTS events (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    site_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    event_date TIMESTAMP NOT NULL,
    location VARCHAR(100),
    event_type VARCHAR(50), -- Restoration, Ceremony, Fundraising, etc.
    organizer VARCHAR(100),
    contact_email VARCHAR(100),
    is_virtual BOOLEAN DEFAULT FALSE,
    virtual_link VARCHAR(255),
    FOREIGN KEY (site_id) REFERENCES heritage_sites(id)
);

-- Restoration Projects Table
CREATE TABLE IF NOT EXISTS restoration_projects (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    site_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PLANNED', -- PLANNED, IN_PROGRESS, COMPLETED
    budget DECIMAL(10, 2),
    funds_raised DECIMAL(10, 2) DEFAULT 0.0,
    project_manager VARCHAR(100),
    contact_email VARCHAR(100),
    FOREIGN KEY (site_id) REFERENCES heritage_sites(id)
);

-- User Sessions Table
CREATE TABLE IF NOT EXISTS user_sessions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    session_token VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiry_date TIMESTAMP,
    ip_address VARCHAR(50),
    user_agent VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

# -- Create indexes for better performance
# CREATE INDEX idx_heritage_sites_category ON heritage_sites(category);
# CREATE INDEX idx_heritage_sites_risk_level ON heritage_sites(risk_level);
# CREATE INDEX idx_media_contributions_user_id ON media_contributions(user_id);
# CREATE INDEX idx_media_contributions_site_id ON media_contributions(site_id);
# CREATE INDEX idx_donations_user_id ON donations(user_id);
# CREATE INDEX idx_donations_site_id ON donations(site_id);
# CREATE INDEX idx_site_visits_user_id ON site_visits(user_id);
# CREATE INDEX idx_site_visits_site_id ON site_visits(site_id);
