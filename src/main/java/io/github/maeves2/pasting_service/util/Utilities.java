package io.github.maeves2.pasting_service.util;

import io.github.maeves2.pasting_service.PasteRepository;
import io.github.maeves2.pasting_service.model.Paste;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Locale;

public class Utilities {
    public static String convertBytesToConvenientUnit(int bytes) {
        if (bytes < 10000) {
            return String.valueOf((float) bytes/1024).substring(0, 4) + "KB";
        } else if (bytes < 10000000) {
            return String.valueOf((float) (bytes/1024)/1024).substring(0, 4) + "MB";
        } else {
            return String.valueOf((float) ((bytes/1024)/1024)/1024).substring(0, 4) + "MB";
        }
    }

    public static String humanReadableDate(Instant instant) {
        String suffix;
        var formatter = new SimpleDateFormat("dd", Locale.ENGLISH);
        if (formatter.format(Date.from(instant)).endsWith(String.valueOf(1))) {
            suffix = "st";
        } else if (formatter.format(Date.from(instant)).endsWith(String.valueOf(2))) {
            suffix = "nd";
        } else if (formatter.format(Date.from(instant)).endsWith(String.valueOf(3))) {
            suffix = "rd";
        } else {
            suffix = "th";
        }

        return new SimpleDateFormat("MMMM dd'" + suffix + "' yyyy, hh:mm:ss ", Locale.ENGLISH).format(Date.from(instant));
    }

    public static void initTestPastes(PasteRepository repository) {
        repository.save(new Paste("0", "C++ example", "#include <iostream>\n" +
                "\n" +
                "int main(int argc, char *argv[]) {\n" +
                "\n" +
                "  /* An annoying \"Hello World\" example */\n" +
                "  for (auto i = 0; i < 0xFFFF; i++)\n" +
                "    cout << \"Hello, World!\" << endl;\n" +
                "\n" +
                "  char c = '\\n';\n" +
                "  unordered_map <string, vector<string> > m;\n" +
                "  m[\"key\"] = \"\\\\\\\\\"; // this is an error\n" +
                "\n" +
                "  return -2e3 + 12l;\n" +
                "}", Instant.EPOCH, "language-cpp"));
        repository.save(new Paste("1", "Java example", "/**\n" +
                " * @author John Smith <john.smith@example.com>\n" +
                "*/\n" +
                "package l2f.gameserver.model;\n" +
                "\n" +
                "public abstract strictfp class L2Char extends L2Object {\n" +
                "  public static final Short ERROR = 0x0001;\n" +
                "\n" +
                "  public void moveTo(int x, int y, int z) {\n" +
                "    _ai = null;\n" +
                "    log(\"Should not be called\");\n" +
                "    if (1 > 5) { // wtf!?\n" +
                "      return;\n" +
                "    }\n" +
                "  }\n" +
                "}", Instant.ofEpochSecond(86400), "language-java"));
        repository.save(new Paste("2", "C# example", "using System.IO.Compression;\n" +
                "\n" +
                "#pragma warning disable 414, 3021\n" +
                "\n" +
                "namespace MyApplication\n" +
                "{\n" +
                "    [Obsolete(\"...\")]\n" +
                "    class Program : IInterface\n" +
                "    {\n" +
                "        public static List<int> JustDoIt(int count)\n" +
                "        {\n" +
                "            Span<int> numbers = stackalloc int[length];\n" +
                "            Console.WriteLine($\"Hello {Name}!\");\n" +
                "            return new List<int>(new int[] { 1, 2, 3 })\n" +
                "        }\n" +
                "    }\n" +
                "}", Instant.ofEpochMilli(1640995200), "language-csharp"));
    }
}
