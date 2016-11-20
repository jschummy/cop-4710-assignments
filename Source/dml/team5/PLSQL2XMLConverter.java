/**
 * @author Matthew Boyette (N00868808@ospreys.unf.edu)
 * @version 1.0
 * 
 * This class converts the relational database output of unmodified Oracle PL/SQL selection statements into XML.
 */

package dml.team5;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import dml.team5.antlr.PLSQLLexer;
import dml.team5.antlr.PLSQLParser;

public class PLSQL2XMLConverter
{
    /**
     * Lexically analyze the input {@link java.lang.String} as an Oracle PL/SQL query and return the {@link dml.team5.antlr.PLSQLParser}.
     * 
     * @param input the input {@link java.lang.String}.
     * @return the {@link dml.team5.antlr.PLSQLParser}.
     * @since 1.0
     */
    protected static final PLSQLParser getParser(final String input)
    {
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        PLSQLLexer lexer = new PLSQLLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new PLSQLParser(tokens);
    }

    /**
     * Console-based command-line test driver. Each argument is an input {@link java.lang.String}.
     * 
     * @param args the command-line arguments.
     * @since 1.0
     */
    public static final void main(final String[] args)
    {
        if ( args.length > 0 )
        {
            for ( int i = 0; i < args.length; i++ )
            {
                if ( i > 0 )
                {
                    System.out.println();
                }

                System.out.println(new PLSQL2XMLConverter(args[i]));
            }
        }
    }

    /**
     * The input {@link java.lang.String}.
     * 
     * @since 1.0
     */
    private String input = "";

    /**
     * The output {@link java.lang.StringBuffer}.
     * 
     * @since 1.0
     */
    private StringBuffer output = new StringBuffer();

    /**
     * Constructs a new instance of {@link dml.team5.PLSQL2XMLConverter} based on an input {@link java.lang.String}.
     * 
     * @param input the input {@link java.lang.String}.
     * @since 1.0
     */
    public PLSQL2XMLConverter(final String input)
    {
        // Trim the input.
        this.setInput(input.trim());
        // Convert the input.
        this.convert();
    }

    /**
     * Constructs the output {@link java.lang.StringBuffer} based on the input {@link java.lang.String}.
     * 
     * @since 1.0
     */
    protected void convert()
    {
        // Declare variables to store the parser, the parse tree, and the query results.
        PLSQLParser parser = null;
        ParseTree tree = null;
        ResultSet results = null;

        try
        {
            // Parse the input query.
            parser = PLSQL2XMLConverter.getParser(this.getInput());
            tree = parser.sql_script();

            try
            {
                // Execute the input query.
                results = Utility.executeSQLStatement(this.getInput());

                // Is the input query a select statement?
                if ( this.getInput().toLowerCase().startsWith("select ") )
                {
                    // TODO: Convert the relational database output of the unmodified Oracle PL/SQL selection statement into XML.
                }
            }
            catch ( final SQLException sqle )
            {
                // Failed to execute the input query.
                sqle.printStackTrace();
            }
        }
        catch ( final RecognitionException re )
        {
            // Failed to parse the input query.
            re.printStackTrace();
        }
    }

    /**
     * Returns the input {@link java.lang.String}.
     * 
     * @return the input {@link java.lang.String}.
     * @since 1.0
     */
    protected final String getInput()
    {
        return this.input;
    }

    /**
     * Returns the output {@link java.lang.StringBuffer}.
     * 
     * @return the output {@link java.lang.StringBuffer}.
     * @since 1.0
     */
    protected final StringBuffer getOutput()
    {
        return this.output;
    }

    /**
     * Allows the input {@link java.lang.String} to be modified.
     * 
     * @param input the input {@link java.lang.String}.
     * @since 1.0
     */
    protected final void setInput(final String input)
    {
        this.input = input;
    }

    /**
     * Allows the output {@link java.lang.StringBuffer} to be modified.
     * 
     * @param output the output {@link java.lang.StringBuffer}.
     * @since 1.0
     */
    protected final void setOutput(final StringBuffer output)
    {
        this.output = output;
    }

    /**
     * Returns a {@link java.lang.String} representation of the output {@link java.lang.StringBuffer}.
     * 
     * @return the output {@link java.lang.String} of the conversion process.
     * @since 1.0
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString()
    {
        return this.getOutput().toString();
    }
}
