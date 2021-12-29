'use strict';
Blockly.Themes.Halloween = Blockly.Theme.defineTheme('halloween', {
    'base': Blockly.Themes.Classic,
    'categoryStyles': {
        'list_category': {
            'colour': "#093a8f",
            'opacity': '0.5'
        },
        'logic_category': {
            'colour': "#8b4513",
        },
        'loop_category': {
            'colour': "#85E21F",
        },
        'text_category': {
            'colour': "#FE9B13",
        }
    },
    'blockStyles': {
        'list_blocks': {
            'colourPrimary': "#4a148c",
            'colourSecondary': "#AD7BE9",
            'colourTertiary': "#CDB6E9"
        },
        'logic_blocks': {
            'colourPrimary': "#8b4513",
            'colourSecondary': "#ff0000",
            'colourTertiary': "#C5EAFF"
        },
        'loop_blocks': {
            'colourPrimary': "#85E21F",
            'colourSecondary': "#ff0000",
            'colourTertiary': "#C5EAFF"
        },
        'text_blocks': {
            'colourPrimary': "#FE9B13",
            'colourSecondary': "#ff0000",
            'colourTertiary': "#C5EAFF"
        }
    },
    'componentStyles': {
        'workspaceBackgroundColour': 'transparent',
        'toolboxBackgroundColour': '#01071657',
        'toolboxForegroundColour': 'transparent',
        'flyoutBackgroundColour': '#08308657',
        'flyoutForegroundColour': 'transparent',
        'flyoutOpacity': 0,
        'scrollbarColour': 'transparent',
        'insertionMarkerColour': '#fff',
        'insertionMarkerOpacity': 0.3,
        'scrollbarOpacity': 0.4,
        'cursorColour': '#d0d0d0',
        'blackBackground': '#333',
    }
});