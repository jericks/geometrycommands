package org.geometrycommands;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class CreateBashCompleteTest {

    static String getArgs(Command command) {
        CmdLineParser cmdLineParser = new CmdLineParser(command.getOptions());
        StringBuilder b = new StringBuilder();
        String[] strings = cmdLineParser.printExample(ExampleMode.ALL).replaceAll("\\(", "").replaceAll("\\)", "").split(" ");
        for(String str : strings) {
            if (str.startsWith("-")) {
                b.append(str).append(" ");
            }
        }
        return b.toString().trim();
    }

    String join(List<String> names, String delimiter) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < names.size(); i++) {
            if(i > 0) {
                str.append(delimiter);
            }
            str.append(names.get(i));
        }
        return str.toString();
    }


    @Test
    public void createBashComplete() throws Exception {

        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        Iterator<Command> iterator = serviceLoader.iterator();
        List<Command> commands = new ArrayList<>();
        while(iterator.hasNext()) {
            Command cmd = iterator.next();
            commands.add(cmd);
        }
        commands.sort(new Comparator<Command>() {
            @Override
            public int compare(Command command, Command t1) {
                return command.getName().compareTo(t1.getName());
            }
        });

        List<String> names = new ArrayList<>();
        for(Command cmd : commands) {
            names.add(cmd.getName());
        }


        String NEW_LINE = System.getProperty("line.separator");

        File file = new File("src/shell/geom_bash_comp");

        StringBuilder builder = new StringBuilder();
        builder.append("_geom()").append(NEW_LINE);
        builder.append("{").append(NEW_LINE);
        builder.append("    local cur=${COMP_WORDS[COMP_CWORD]}").append(NEW_LINE);
        builder.append("    local prev=${COMP_WORDS[COMP_CWORD-1]}").append(NEW_LINE);
        builder.append("    local line=${COMP_LINE}").append(NEW_LINE);
        builder.append("    COMPREPLY=()").append(NEW_LINE);
        // geom -> list all commands
        builder.append("    if [[ \"$line\" == *\"geom \" ]]; then").append(NEW_LINE);
        builder.append("        COMPREPLY=($(compgen -W '" + join(names," ") + "' -- $cur))").append(NEW_LINE);
        // geom buffer -> exact matches, so list arguments
        for(Command cmd : commands) {
            String name = cmd.getName();
            String commandArgs = getArgs(cmd);
            builder.append("    elif [[ \"$line\" == *\"geom " + cmd.getName() + " \"* ]]; then").append(NEW_LINE);
            builder.append("        COMPREPLY=($(compgen -W '" + commandArgs + "' -- $cur))").append(NEW_LINE);
        }

        // geom b -> partial match, list all commands starting with
        builder.append("    elif [[ \"$line\" == *\"geom\"[[:space:]][[:alpha:]]* ]]; then").append(NEW_LINE);
        builder.append("        local nm=\"${line##*geom }\"").append(NEW_LINE);
        for(Command cmd : commands) {
            String name = cmd.getName();
            builder.append("        if [[ \"" + name + "\" == \"$nm\"* ]]; then").append(NEW_LINE);
            builder.append("            COMPREPLY=(\"${COMPREPLY[@]}\" $(compgen -W '" + name + "'))").append(NEW_LINE);
            builder.append("        fi").append(NEW_LINE);
        }
        builder.append("    fi").append(NEW_LINE);
        builder.append("    return 0").append(NEW_LINE);
        builder.append("} && complete -f -d -F _geom geom").append(NEW_LINE);

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(builder.toString());
        fileWriter.close();
    }

}
