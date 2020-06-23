package ui.listeners;

import exceptions.EmployerException;

public interface UserLoginListener {
    public void onSuccess();
    public void onError(String message);
    public void onError(EmployerException exception);
}