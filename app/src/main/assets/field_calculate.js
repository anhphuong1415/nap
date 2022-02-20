'use strict';

goog.provide('CustomFields.FieldCalculate');

goog.require('Blockly.FieldNumber');
goog.require('Blockly.Field');
goog.require('Blockly.fieldRegistry');
goog.require('Blockly.utils.math');
goog.require('Blockly.utils');
goog.require('Blockly.utils.dom');
goog.require('Blockly.utils.object');
goog.require('Blockly.utils.Size');

var CustomFields = CustomFields || {};

//constructor
CustomFields.FieldCalculate = function(opt_value, opt_min, opt_max, opt_precision) {

    this.displayValue_ = opt_value;
    this.max_ = opt_max;
    this.min_ = opt_min;
    CustomFields.FieldCalculate.superClass_.constructor.call(this, opt_value, opt_min, opt_max, opt_precision);
};
Blockly.utils.object.inherits(CustomFields.FieldCalculate, Blockly.FieldNumber);

CustomFields.FieldCalculate.prototype.CURSOR = 'pointer';
CustomFields.FieldCalculate.prototype.editorListeners_ = [];
CustomFields.FieldCalculate.prototype.calScreenVal = 0;

//on Block display
CustomFields.FieldCalculate.prototype.initView = function() {
    CustomFields.FieldCalculate.superClass_.initView.call(this);
    // this.createView_();
}

CustomFields.FieldCalculate.fromJson = function(options) {
    return new CustomFields.FieldCalculate(options['value'],
        undefined, undefined, undefined, undefined, options);
};

//
CustomFields.FieldCalculate.prototype.createView_ = function() {

}

// Save the new field value
CustomFields.FieldCalculate.prototype.doValueUpdate_ = function(newValue) {
    CustomFields.FieldCalculate.superClass_.doValueUpdate_.call(this, newValue);
    this.displayValue_ = newValue;
    this.isValueInvalid_ = false;
}

//Custom new Widget editor
CustomFields.FieldCalculate.prototype.showEditor_ = function() {
    CustomFields.FieldCalculate.superClass_.showEditor_.call(this);
    this.editor_ = this.createUI();
    // this.renderEditor_();
    Blockly.DropDownDiv.getContentDiv().appendChild(this.editor_);

    var fillColour = this.sourceBlock_.getColour();
    Blockly.DropDownDiv.setColour(fillColour,
        this.sourceBlock_.style.colourTertiary);

    // Always pass the dropdown div a dispose function so that you can clean
    // up event listeners when the editor closes.
    Blockly.DropDownDiv.showPositionedByField(
        this, this.dropdownDispose_.bind(this));
}

CustomFields.FieldCalculate.prototype.createUI = function() {
    var createRow = function(table) {
        var row = table.appendChild(document.createElement('div'));
        row.className = 'row';
        return row;
    };

    var createNum = function(row, value) {
        var cell = document.createElement('div');
        var button = document.createElement('button');
        cell.className = 'button_n';
        button.setAttribute('type', 'button');
        button.textContent = value;
        cell.appendChild(button);
        row.appendChild(cell);
        return cell;
    }

    var createButtonListener = function(buttonName) {
        return function() {
            let val = this.getValue();
            if (buttonName == 'CE') {
                val = 0;
            } else if (buttonName == 'x') {
                val = (val - val % 10) / 10;
            } else {
                // TODO
                val = val * 10 + buttonName;
            }
            this.setValue(val)
            this.editor_.calScreen.textContent = val;
            this.isDirty_ = true;
        }
    }

    var createTextNode = function(row, val) {
        row.id = 'textRow';
        var cell = document.createElement('div');
        cell.className = 'text';
        var text = document.createTextNode(val);
        cell.appendChild(text);
        cell.textElement = text;
        row.appendChild(cell);
        return cell;
    };

    var widget = document.createElement('div');
    widget.className = 'customFieldsCalWidget';

    var table = document.createElement('div');
    table.className = 'table';
    widget.appendChild(table);

    var row1 = createRow(table);
    widget.calScreen = createTextNode(row1, this.getValue());

    for (let i = 0; i < 3; i++) {
        var row = createRow(table);
        for (let j = 1; j < 4; j++) {
            var value = i * 3 + j;
            var button = createNum(row, value);
            this.editorListeners_.push(Blockly.bindEvent_(button, 'mouseup', this,
                createButtonListener(value)));
        }
    }

    var row4 = createRow(table);
    var Button_0 = createNum(row4, '0');
    this.editorListeners_.push(Blockly.bindEvent_(Button_0, 'mouseup', this,
        createButtonListener('0')));

    var Button_Clear = createNum(row4, '<=');
    this.editorListeners_.push(Blockly.bindEvent_(Button_Clear, 'mouseup', this,
        createButtonListener('x')));

    var Button_CE = createNum(row4, 'CE');
    this.editorListeners_.push(Blockly.bindEvent_(Button_CE, 'mouseup', this,
        createButtonListener('CE')));

    return widget;
}

// Update display value
CustomFields.FieldCalculate.prototype.render_ = function() {
    var value = this.displayValue_;
    // this.textContent_.nodeValue = 12;
    CustomFields.FieldCalculate.superClass_.render_.call(this);

    if (this.editor_) {
        this.renderEditor_();
    }
}

CustomFields.FieldCalculate.prototype.renderEditor_ = function() {
    this.editor_.calScreen.textContent = this.displayValue_;
}

// Cleans up any event listeners that were attached to the now hidden editor.
CustomFields.FieldCalculate.prototype.dropdownDispose_ = function() {
    for (var i = this.editorListeners_.length, listener; listener = this.editorListeners_[i]; i--) {
        Blockly.unbindEvent_(listener);
        this.editorListeners_.pop();
    }
};

//hide
CustomFields.FieldCalculate.prototype.hide_ = function() {
    Blockly.DropDownDiv.hideWithoutAnimation();
    this.dropdownDispose_();
}

CustomFields.FieldCalculate.prototype.showPromptEditor_ = function() {};

//Add field to the map
Blockly.fieldRegistry.register('field_calculator', CustomFields.FieldCalculate);