package me.schf.personal.config;

public record MongoConnection(
	    String cluster,
	    String clusterHost,
	    String dbName,
	    String dbPassword,
	    String dbUser
	) {
	    public String buildConnectionString() {
	        return "mongodb+srv://%s:%s@%s/?retryWrites=true&w=majority&appName=%s"
	                .formatted(dbUser, dbPassword, clusterHost, cluster);
	    }
	}