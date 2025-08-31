//package com.example.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.quarkus.security.identity.AuthenticationRequestContext;
//import io.quarkus.security.identity.SecurityIdentity;
//import io.quarkus.security.identity.SecurityIdentityAugmentor;
//import io.quarkus.security.runtime.QuarkusSecurityIdentity;
//import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;
//import io.smallrye.mutiny.Uni;
//import jakarta.enterprise.context.ApplicationScoped;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//import java.util.function.Supplier;
//
//@ApplicationScoped
//public class RolesAugmentor implements SecurityIdentityAugmentor {
//
//    @Override
//    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
//        try {
//            return Uni.createFrom().item(build(identity));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Do 'return context.runBlocking(build(identity));'
//        // if a blocking call is required to customize the identity
//    }
//
//    private Supplier<SecurityIdentity> build(SecurityIdentity identity) throws IOException {
//
//        List<String> roles = new ArrayList<>();
//        if (identity.getPrincipal() instanceof DefaultJWTCallerPrincipal jwtPrincipal) {
//            String rawToken = jwtPrincipal.getRawToken();
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(Base64.getUrlDecoder().decode(rawToken.split("\\.")[1]));
//            JsonNode rolesNode = root.path("roles");
//
//            for (JsonNode role : rolesNode) {
//                roles.add(role.asText());
//            }
//        }
//
//
//        if (identity.isAnonymous()) {
//            return () -> identity;
//        } else {
//            // create a new builder and copy principal, attributes, credentials and roles from the original identity
//            QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder(identity);
//
//            // add custom role source here
//            for (String role : roles)
//                builder.addRole(role);
//            return builder::build;
//        }
//    }
//}
