package com.security.app.service;

import com.security.app.model.UserAttempts;

public interface UserAttemptsManager {
	void updateAttempts(UserAttempts userAttempts);
	UserAttempts getUserAttempts(String username);
}
