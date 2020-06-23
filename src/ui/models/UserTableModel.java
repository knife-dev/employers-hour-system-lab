package ui.models;

import entities.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private static final int USERID   = 0;
    private static final int EMAIL    = 1;
    private static final int PASSWORD = 2;
    private static final int ROLE     = 3;

    private String[] headers = {"userId", "Email", "Password", "Role"};

    private List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    public void notifyDataChanged() {
        this.fireTableDataChanged();
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        User user = users.get(rowIndex);
        switch(columnIndex) {
            case USERID   :     return user.getId();
            case EMAIL    :     return user.getEmail();
            case PASSWORD :     return user.getPassword();
            case ROLE     :     return user.getRole();
            default       :     return null;
        }
    }

    public User getUser(int selectedRow) {
        if(selectedRow < 0) {
            return null;
        }
        return users.get(selectedRow);
    }

    public void addUser(User user) {
        int firstRow = this.getRowCount();
        users.add(user);
        this.fireTableRowsInserted(firstRow, this.getRowCount()); // repaint table
    }

    public User removeUser(int selectedRow) {
        if(selectedRow < 0) {
            return null;
        }
        return users.remove(selectedRow);
    }
}
