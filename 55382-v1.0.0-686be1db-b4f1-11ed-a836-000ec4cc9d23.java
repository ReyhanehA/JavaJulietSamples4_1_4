/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE497_Information_Leak_of_System_Data__leakStacktraceServlet_13.java
Label Definition File: CWE497_Information_Leak_of_System_Data.label.xml
Template File: point-flaw-13.tmpl.java
*/
/*
* @description
* CWE: 497 Information Leak
* Sinks: leakStacktraceServlet
*    GoodSink: no leaking
*    BadSink : leak the path variable to the user
* Flow Variant: 13 Control flow: if(IO.static_final_five==5) and if(IO.static_final_five!=5)
*
* */

package testcases.CWE497_Information_Leak_of_System_Data;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;

public class CWE497_Information_Leak_of_System_Data__leakStacktraceServlet_13 extends AbstractTestCaseServlet
{

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if (IO.static_final_five==5)
        {
            try
            {
                throw new Exception("Testing!");
            }
            catch(Exception e)
            {
                /* Note: this will likely result in an incidental issue for using .printStackTrace() */
                e.printStackTrace(response.getWriter()); /* FLAW: stack trace details */
            }
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            try
            {
                throw new Exception("Testing!");
            }
            catch(Exception e)
            {
                IO.writeLine("An error occurred!"); /* FIX: sensitive system details omitted in output */
            }

        }
    }

    /* good1() changes IO.static_final_five==5 to IO.static_final_five!=5 */
    private void good1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if(IO.static_final_five!=5)
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            try
            {
                throw new Exception("Testing!");
            }
            catch(Exception e)
            {
                /* Note: this will likely result in an incidental issue for using .printStackTrace() */
                e.printStackTrace(response.getWriter()); /* FLAW: stack trace details */
            }
        }
        else {

            try
            {
                throw new Exception("Testing!");
            }
            catch(Exception e)
            {
                IO.writeLine("An error occurred!"); /* FIX: sensitive system details omitted in output */
            }

        }
    }

    /* good2() reverses the bodies in the if statement */
    private void good2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if(IO.static_final_five==5)
        {
            try
            {
                throw new Exception("Testing!");
            }
            catch(Exception e)
            {
                IO.writeLine("An error occurred!"); /* FIX: sensitive system details omitted in output */
            }
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            try
            {
                throw new Exception("Testing!");
            }
            catch(Exception e)
            {
                /* Note: this will likely result in an incidental issue for using .printStackTrace() */
                e.printStackTrace(response.getWriter()); /* FLAW: stack trace details */
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
