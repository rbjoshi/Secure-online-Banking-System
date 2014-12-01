package com.security.app.dao;

import com.security.app.model.UserAttempts;

public interface UserAttemptsDao {
	void updateAttempts(UserAttempts userAttempts);
	UserAttempts getUserAttempts(String username);
}
