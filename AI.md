# Use of AI Tools

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

4. 
