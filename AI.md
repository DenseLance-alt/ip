# Use of AI Tools

#### To improve code quality

I have asked GitHub Copilot, specifically Claude Haiku 4.5, the following:

> Is there any way I can improve my code for my chatbot, such that it 
> obeys software engineering principles such as SLAP, DRY, and good 
> naming?

I have applied the following recommendations provided by the LLM:

1. <u>Documentation:</u> Missing/Incomplete Javadoc in `YoshikageKira.processResponseForGui()`.
   
   **Before:**
   
   ```java
   /**
    *  // <-- Empty
    */
   public static String processResponseForGui(String response, boolean removeLineSeparator) {
   ```
   
   **After:**
   
   ```java
   /**
    * Processes chatbot response for GUI display by removing formatting characters.
    * @param response The raw response from the chatbot.
    * @param removeLineSeparator Whether to remove line separators.
    * @return Processed response suitable for GUI display.
    */
   public static String processResponseForGui(String response, boolean removeLineSeparator) {
   ```

2. <u>Inconsistent Naming:</u> `isDone` is ambiguous. In `Ui.java`, `isDone` could mean "task is done" or "program is done".
   
   **Before:**
   
   ```java
   private boolean isDone;
   public boolean isDone() { return isDone; }
   ```
   
   **After:**
   
   ```java
   private boolean isProgramTerminated;
   public boolean isProgramTerminated() { return isProgramTerminated; }
   
   // In YoshikageKira.java
   while (!ui.isProgramTerminated()) { ... }
   ```

3. <u>Poor Naming:</u> Magic Strings. Task categories should be named constants, not magic strings.
   
   **Before:**
   
   ```java
   @Override
   public String getCategory() {
       return "T";
   }
   ```
   
   **After:**
   
   ```java
   public enum TaskCategory {
       TODO("T"),
       DEADLINE("D"),
       EVENT("E");
   
       private final String code;
       TaskCategory(String code) { this.code = code; }
       public String getCode() { return code; }
   }
   
   // In ToDo.java
   @Override
   public String getCategory() {
       return TaskCategory.TODO.getCode();
   }
   ```

#### To provide more JUnit tests

I have asked GitHub Copilot, specifically Claude Haiku 4.5, the following:

> Provide more JUnit tests for my code.

I have applied the following recommendations provided by the LLM:

1. `CommandParserTest.java`
   
   ```java
   package chatbot.parser;
   
   import static org.junit.jupiter.api.Assertions.assertNotNull;
   import static org.junit.jupiter.api.Assertions.assertThrows;
   
   import org.junit.jupiter.api.Test;
   
   import chatbot.command.ByeCommand;
   import chatbot.command.ClearCommand;
   import chatbot.command.Command;
   import chatbot.command.DeadlineCommand;
   import chatbot.command.DeleteCommand;
   import chatbot.command.EventCommand;
   import chatbot.command.FindCommand;
   import chatbot.command.ListCommand;
   import chatbot.command.MarkCommand;
   import chatbot.command.SortCommand;
   import chatbot.command.ToDoCommand;
   import chatbot.command.UnknownCommand;
   import chatbot.command.UnmarkCommand;
   import chatbot.exception.ChatbotException;
   
   public class CommandParserTest {
       @Test
       void testParseByeCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("bye");
           assert command instanceof ByeCommand;
       }
   
       @Test
       void testParseClearCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("clear");
           assert command instanceof ClearCommand;
       }
   
       @Test
       void testParseListCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("list");
           assert command instanceof ListCommand;
       }
   
       @Test
       void testParseFindCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("find homework");
           assert command instanceof FindCommand;
       }
   
       @Test
       void testParseSortCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("sort");
           assert command instanceof SortCommand;
       }
   
       @Test
       void testParseMarkCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("mark 1");
           assert command instanceof MarkCommand;
       }
   
       @Test
       void testParseUnmarkCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("unmark 1");
           assert command instanceof UnmarkCommand;
       }
   
       @Test
       void testParseToDoCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("todo Buy groceries");
           assert command instanceof ToDoCommand;
       }
   
       @Test
       void testParseDeadlineCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("deadline Submit /by 1/1/2026 18:00");
           assert command instanceof DeadlineCommand;
       }
   
       @Test
       void testParseEventCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("event Meeting /from 1/1/2026 10:00 /to 1/1/2026 12:00");
           assert command instanceof EventCommand;
       }
   
       @Test
       void testParseDeleteCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("delete 1");
           assert command instanceof DeleteCommand;
       }
   
       @Test
       void testParseUnknownCommand() throws ChatbotException {
           Command command = CommandParser.parseCommand("unknown");
           assert command instanceof UnknownCommand;
       }
   
       @Test
       void testParseCommandCaseInsensitive() throws ChatbotException {
           Command cmd1 = CommandParser.parseCommand("bye");
           Command cmd2 = CommandParser.parseCommand("BYE");
           Command cmd3 = CommandParser.parseCommand("Bye");
           assert cmd1 instanceof ByeCommand;
           assert cmd2 instanceof ByeCommand;
           assert cmd3 instanceof ByeCommand;
       }
   
       @Test
       void testParseInvalidCharacterThrowsException() {
           assertThrows(ChatbotException.class, () -> CommandParser.parseCommand("todo test\\|invalid"));
       }
   }
   ```

2. 
