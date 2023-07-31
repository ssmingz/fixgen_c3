import os
import click
import json
import difflib
from prompt_toolkit import prompt
from prompt_toolkit.styles import Style
from prompt_toolkit.shortcuts import print_formatted_text
from prompt_toolkit.formatted_text import FormattedText


@click.command()
@click.option('--load_json', '-l', help='Path to the JSON files.', type=click.Path(exists=True))
@click.option('--decision_file', '-d', default='decisions.txt', help='Output file to save user decisions.')
@click.option('--start_at', '-s', default=0, help='Start at the given index.')
def main(load_json, decision_file, start_at):
    # Define the TUI styles
    style = Style.from_dict({
        'prompt': 'bg:ansiyellow fg:black',
        'diff_header': 'fg:ansiblue',
        'diff_add': 'fg:ansigreen',
        'diff_remove': 'fg:ansired',
    })

    dataset = json.load(open(load_json, 'r'))

    for idx, cluster in enumerate(dataset[start_at:]):
        if idx != 543:
            continue
        # Show the diff between the two methods using TUI
        diff_text = FormattedText([
            ('class:diff_header', 'Diff:\n'),
        ])

        # Iterate over all the methodPairs in the JSON data
        for pair in cluster:
            method_before = pair['methodBefore']
            method_after = pair['methodAfter']

            diff_lines = difflib.unified_diff(
                method_before.splitlines(),
                method_after.splitlines(),
                lineterm='',
            )

            for line in diff_lines:
                if line.startswith('+++') or line.startswith('---') or line.startswith('@@'):
                    diff_text.append(('class:diff_header', line + "\n"))
                elif line.startswith('+'):
                    diff_text.append(('class:diff_add', line + "\n"))
                elif line.startswith('-'):
                    diff_text.append(('class:diff_remove', line + "\n"))
                else:
                    diff_text.append(('class:', line + "\n"))

        print_formatted_text(diff_text, style=style)

        # Ask the user to judge the methodPair using TUI
        decision = prompt(
            f'[{idx+start_at}/{len(dataset)}]: Is this methodPair good or not good? (g/n)',
            style=style
        )

        # Save the user decision to a file
        with open(decision_file, 'a') as f:
            f.write(f'{start_at + idx}: {decision}\n')

        # Clear the screen after the user makes a decision
        os.system('cls' if os.name == 'nt' else 'clear')

    click.echo(f'User decisions saved to {decision_file}')


if __name__ == '__main__':
    main()