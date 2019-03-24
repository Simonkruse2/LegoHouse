package Logic;

public class User {
    
    private String email;
    private String password;
    private boolean admin;

    public User(String email, boolean admin) {
        this.email = email;
        this.admin = admin;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin( boolean role ) {
        this.admin = role;
    }

    @Override
    public String toString() {
        return "Email: " + email + " Password: " + password + " Admin: " + admin;
    }
    
}
