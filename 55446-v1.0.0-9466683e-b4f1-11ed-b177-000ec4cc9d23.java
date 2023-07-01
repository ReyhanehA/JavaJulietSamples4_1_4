/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE533_Info_Leak_Server_Log__Servlet_05.java
Label Definition File: CWE533_Info_Leak_Server_Log__Servlet.label.xml
Template File: point-flaw-05.tmpl.java
*/
/*
* @description
* CWE: 533 Information Leak through Server Log Files
* Sinks:
*    GoodSink: log non-sensitive information to the server log
*    BadSink : leak the session ID to the server log
* Flow Variant: 05 Control flow: if(private_t) and if(private_f)
*
* */

package testcases.CWE533_Info_Leak_Server_Log;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;

public class CWE533_Info_Leak_Server_Log__Servlet_05 extends AbstractTestCaseServlet
{

    /* The two variables below are not defined as "final", but are never
       assigned any other value, so a tool should be able to identify that
       reads of these will always return their initialized values. */
    private boolean private_t = true;
    private boolean private_f = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if (private_t)
        {
            if (request.getParameter("username") == null)
            {
                return;
            }
            String username = request.getParameter("username");
            if (username.matches("[a-zA-Z0-9]*"))
            {
                HttpSession session = request.getSession(true);
                /* FLAW: leak session ID to server log */
                log("Username: " + username + " Session ID:" + session.getId());
            }
            else
            {
                response.getWriter().println("Invalid characters");
            }
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            if (request.getParameter("username") == null)
            {
                return;
            }

            String username = request.getParameter("username");

            if (username.matches("[a-zA-Z0-9]*"))
            {
                HttpSession session = request.getSession(true);
                /* FIX: logged message does not contain session id */
                log("Username: " + username + " logged in");
            }
            else
            {
                response.getWriter().println("Invalid characters");
            }

        }
    }

    /* good1() changes private_t to private_f */
    private void good1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if(private_f)
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            if (request.getParameter("username") == null)
            {
                return;
            }
            String username = request.getParameter("username");
            if (username.matches("[a-zA-Z0-9]*"))
            {
                HttpSession session = request.getSession(true);
                /* FLAW: leak session ID to server log */
                log("Username: " + username + " Session ID:" + session.getId());
            }
            else
            {
                response.getWriter().println("Invalid characters");
            }
        }
        else {

            if (request.getParameter("username") == null)
            {
                return;
            }

            String username = request.getParameter("username");

            if (username.matches("[a-zA-Z0-9]*"))
            {
                HttpSession session = request.getSession(true);
                /* FIX: logged message does not contain session id */
                log("Username: " + username + " logged in");
            }
            else
            {
                response.getWriter().println("Invalid characters");
            }

        }
    }

    /* good2() reverses the bodies in the if statement */
    private void good2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if(private_t)
        {
            if (request.getParameter("username") == null)
            {
                return;
            }
            String username = request.getParameter("username");
            if (username.matches("[a-zA-Z0-9]*"))
            {
                HttpSession session = request.getSession(true);
                /* FIX: logged message does not contain session id */
                log("Username: " + username + " logged in");
            }
            else
            {
                response.getWriter().println("Invalid characters");
            }
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            if (request.getParameter("username") == null)
            {
                return;
            }

            String username = request.getParameter("username");

            if (username.matches("[a-zA-Z0-9]*"))
            {
                HttpSession session = request.getSession(true);
                /* FLAW: leak session ID to server log */
                log("Username: " + username + " Session ID:" + session.getId());
            }
            else
            {
                response.getWriter().println("Invalid characters");
            }

        }

    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        good1(request, response);
        good2(request, response);
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}
