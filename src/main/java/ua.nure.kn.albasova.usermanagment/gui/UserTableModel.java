package ua.nure.kn.kukhtin.usermanagment.gui;

import ua.nure.kn.kukhtin.usermanagment.User;
import ua.nure.kn.kukhtin.usermanagment.util.Messages;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {Messages.getString("user_managment.userTable"), Messages.getString("user_managment.userTable2"), Messages.getString("user_managment.userTable3")};
    private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
    private List users = null;

    public UserTableModel(Collection users) {
        this.users = new ArrayList(users);
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = (User) users.get(rowIndex);
        switch (columnIndex){
            case 0:
                return user.getId();
            case 1:
                return user.getFirstName();
            case 2:
                return user.getLastName();
        }
        return null;
    }
    public void addUsers(Collection users) {
        this.users.addAll(users);

    }

    public void clearUsers() {
        this.users = new ArrayList();
    }
}
