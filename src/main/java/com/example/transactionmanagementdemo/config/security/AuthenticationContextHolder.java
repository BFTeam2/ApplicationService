package com.example.transactionmanagementdemo.config.security;

import org.springframework.security.core.Authentication;

/**
 * Authentication context holder
 * Holds authentication information for the current thread
 * @author
 */
public class AuthenticationContextHolder
{
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext()
    {
        return contextHolder.get();
    }

    public static void setContext(Authentication context)
    {
        contextHolder.set(context);
    }

    public static void clearContext()
    {
        contextHolder.remove();
    }
}
