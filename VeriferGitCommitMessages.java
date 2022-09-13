public class VeriferGitCommitMessages {
    public static final String[] VERBS = {"Summarize"};

    public static void main(String[] args) {
        String commitMessage = "Summarize changes in around 50 characters or less\n" + "\n"
                + "More detailed explanatory text, if necessary. Wrap it to about 72\n"
                + "characters or so. In some contexts, the first line is treated as the\n"
                + "subject of the commit and the rest of the text as the body. The\n"
                + "blank line separating the summary from the body is critical (unless\n"
                + "you omit the body entirely); various tools like `log`, `shortlog`\n"
                + "and `rebase` can get confused if you run the two together.\n" + "\n"
                + "Explain the problem that this commit is solving. Focus on why you\n"
                + "are making this change as opposed to how (the code explains that).\n"
                + "Are there side effects or other unintuitive consequences of this\n"
                + "change? Here's the place to explain them.";
        validateGitCommitMessage(commitMessage);
    }

    private static void validateGitCommitMessage(final String commitMessage) {
        if(isTheStringEmty(commitMessage)){
            System.exit(100);
        };
        if(!isFirstLetterTitle(commitMessage)){
            System.exit(200);
        };
        if(!isFirstWordVerb(commitMessage)){
            System.exit(300);
        };
        if(!getSubject(commitMessage)){
            System.exit(400);
        };



        System.out.println("Message is valid!");
    }

    private static boolean getBody(String commitMessage, int endPointSubject) {
        var startBody = 0;
        var endBody = 0;
        startBody = endPointSubject + 1;
        endBody = commitMessage.length() - 1;
        var numberOfLines = countLinesInBody(commitMessage, startBody);
        var numberSymbolsOfBody = (startBody - endBody) / numberOfLines;
        if (numberSymbolsOfBody <= 72) {
            return true;
        }
        return false;
    }

    private static int countLinesInBody(String commitMessage, int startBody) {
        int count=0;
        for (int i = startBody; i <commitMessage.length() ; i++) {
            var ch = commitMessage.charAt(i);
            if(ch=='\n'){
                count++;
            }

        }
        return count;
    }


    private static boolean getSubject(String commitMessage) {
        var ch = commitMessage.charAt(0);
        for (int i = 1; i < commitMessage.length(); i++) {
            var chNext = commitMessage.charAt(i);

            if (ch == chNext && ch == '\n') {
                var endPointSubject = i;
                if (endPointSubject <= 50) {
                    if (commitMessage.charAt(i - 1) != '.') {
                        return getBody(commitMessage, endPointSubject);
                    }

                }
                return false;
            } else {
                ch = chNext;
            }
        }
        return false;
    }

    private static boolean isFirstWordVerb(String commitMessage) {
        StringBuilder wordBuilder = new StringBuilder();

        for (int i = 0; i < commitMessage.length(); i++) {
            var ch = commitMessage.charAt(i);
            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                wordBuilder.append(ch);
            } else {
                break;
            }
        }
        for (String word : VERBS) {
            if (word.equals(wordBuilder.toString())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFirstLetterTitle(String commitMessage) {
        String firstSymbol = Character.toString(commitMessage.charAt(0));

        return firstSymbol.equals(firstSymbol.toUpperCase());

    }

    private static boolean isTheStringEmty(String commitMessage) {
        return commitMessage.isEmpty();

    }

}

