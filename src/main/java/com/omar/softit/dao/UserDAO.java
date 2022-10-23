package com.omar.softit.dao;

import com.omar.softit.beans.User;
import com.omar.softit.utils.NeedsContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface UserDAO extends NeedsContext {
     void prepare() throws IOException;
     User getUser(long id);
     void delete(User user);
     void update(User user);
     void save(User user);
      List<User> getAll();
}
