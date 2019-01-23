package DAO;

import beans.User;

public interface DAOUser {

  public Long getUsers(String username);

  public User getUser(long userId);

  public void updateUser();

  public void addUser();

}