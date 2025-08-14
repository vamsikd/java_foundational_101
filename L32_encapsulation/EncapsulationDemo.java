package L32_encapsulation;

public class EncapsulationDemo {
    public static void main(String[] args) {
        UserAccount acc = new UserAccount("alpha", "alpha@example.com");
        System.out.println("User: " + acc.getUsername() + ", email=" + acc.getEmail());
        acc.setEmail("badEmail");
        acc.setEmail("new@example.com");
        System.out.println("Updated email: " + acc.getEmail());
        System.out.println("Account id (read-only): " + acc.getId());
        SecretHolder holder = new SecretHolder(new char[] { 't', 'o', 'k', 'e', 'n' });
        char[] leakAttempt = holder.getSecret();
        leakAttempt[0] = 'X';
        System.out.println("Internal secret intact? first char = " + holder.getSecret()[0]);
        LazyReport r = new LazyReport();
        System.out.println("First size calc: " + r.size());
        System.out.println("Second size calc (cached): " + r.size());
        FluentConfig cfg = new FluentConfig().withHost("localhost").withPort(8080);
        System.out.println("Config: host=" + cfg.getHost() + " port=" + cfg.getPort());
        ImmutablePoint pt = new ImmutablePoint(3, 4);
        System.out.println("Point lengthSquared=" + pt.lengthSquared());
        PackagePrivateDemo demo = new PackagePrivateDemo();
        System.out.println("PackagePrivate demo message: " + demo.message());
        SubUser su = new SubUser("beta", "beta@example.com");
        su.promote();
        BuiltUser built = new BuiltUser.Builder().username("gamma").email("g@example.com").build();
        System.out.println("Built user: " + built.getUsername());
    }
}

class UserAccount {
    private static long nextId = 1;
    private final long id;
    private String username;
    private String email;
    protected String role = "USER";

    UserAccount(String u, String e) {
        id = nextId++;
        username = u;
        setEmail(e);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String u) {
        if (u == null || u.isBlank()) {
            System.out.println("Rejected empty username");
            return;
        }
        username = u;
    }

    public void setEmail(String e) {
        if (e == null || !e.contains("@")) {
            System.out.println("Rejected invalid email: " + e);
            return;
        }
        email = e;
    }

    protected void setRole(String r) {
        role = r;
    }
}

class SubUser extends UserAccount {
    SubUser(String u, String e) {
        super(u, e);
    }

    void promote() {
        setRole("ADMIN");
        System.out.println("Promoted user " + getUsername() + " to role ADMIN");
    }
}

class SecretHolder {
    private final char[] secret;

    SecretHolder(char[] s) {
        secret = s.clone();
    }

    char[] getSecret() {
        return secret.clone();
    }
}

class LazyReport {
    private int[] data;
    private Integer cachedSize;

    int size() {
        if (cachedSize == null) {
            if (data == null) {
                data = new int[10];
            }
            cachedSize = data.length;
            System.out.println("(computed size)");
        }
        return cachedSize;
    }
}

class FluentConfig {
    private String host = "127.0.0.1";
    private int port = 80;

    FluentConfig withHost(String h) {
        host = h;
        return this;
    }

    FluentConfig withPort(int p) {
        port = p;
        return this;
    }

    String getHost() {
        return host;
    }

    int getPort() {
        return port;
    }
}

final class ImmutablePoint {
    private final int x;
    private final int y;

    ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int lengthSquared() {
        return x * x + y * y;
    }
}

class PackagePrivateDemo {
    String message() {
        return "Hello from package-private class";
    }
}

class BuiltUser {
    private final String username;
    private final String email;

    private BuiltUser(Builder b) {
        username = b.username;
        email = b.email;
    }

    String getUsername() {
        return username;
    }

    String getEmail() {
        return email;
    }

    static class Builder {
        private String username;
        private String email;

        Builder username(String u) {
            username = u;
            return this;
        }

        Builder email(String e) {
            email = e;
            return this;
        }

        BuiltUser build() {
            if (username == null || email == null || !email.contains("@"))
                throw new IllegalStateException("Invalid user data");
            return new BuiltUser(this);
        }
    }
}
