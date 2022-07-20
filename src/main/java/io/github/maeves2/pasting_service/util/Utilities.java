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
        }
        if (bytes < 10000000) {
            return String.valueOf((float) (bytes/1024)/1024).substring(0, 4) + "MB";
        }
        return String.valueOf((float) ((bytes/1024)/1024)/1024).substring(0, 4) + "MB";
    }

    public static String humanReadableDate(Instant instant) {
        var days = Integer.parseInt(new SimpleDateFormat("dd", Locale.ENGLISH).format(Date.from(instant)));
        var suffix = switch (days) {
            case 1, 21, 31 -> "st";
            case 2, 22 -> "nd";
            case 3, 23 -> "rd";
            default -> "th";
        };


        return new SimpleDateFormat("MMMM dd'" + suffix + "' yyyy, hh:mm:ss ", Locale.ENGLISH).format(Date.from(instant));
    }

    // im sorry for hardcoding this in ;_;
    public static String getErrorMessage(int status) {
        return switch (status) {
            case 300 -> "Multiple Choices";
            case 301 -> "Moved Permanently";
            case 302 -> "Found";
            case 303 -> "See Other";
            case 304 -> "Not Modified";
            case 305 -> "Use Proxy";
            case 307 -> "Temporary Redirect";
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 402 -> "Payment Required";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            case 406 -> "Not Acceptable";
            case 407 -> "Proxy Authentication Required ";
            case 408 -> "Request Timeout";
            case 409 -> "Conflict";
            case 410 -> "Gone";
            case 411 -> "Length Required";
            case 412 -> "Precondition Failed";
            case 413 -> "Payload Too Large";
            case 414 -> "URI Too Long";
            case 415 -> "Unsupported Media Type";
            case 416 -> "Range Not Satisfiable";
            case 417 -> "Expectation Failed";
            case 418 -> "I'm a teapot";
            case 426 -> "Upgrade Required";
            case 500 -> "Internal Server Error";
            case 501 -> "Not Implemented";
            case 502 -> "Bad Gateway";
            case 503 -> "Service Unavailable";
            case 504 -> "Gateway Timeout";
            case 505 -> "HTTP Version Not Supported";
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }

    // examples to test formatting
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
