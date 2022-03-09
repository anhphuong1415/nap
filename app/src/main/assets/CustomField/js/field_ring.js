'use strict';


// You must provide the constructor for your custom field.
goog.provide('CustomFields.FieldRing');

// You must require the abstract field class to inherit from.
goog.require('Blockly.browserEvents');
goog.require('Blockly.Field');
goog.require('Blockly.fieldRegistry');
goog.require('Blockly.utils');
goog.require('Blockly.utils.dom');
goog.require('Blockly.utils.object');
goog.require('Blockly.utils.Size');

var CustomFields = CustomFields || {};

CustomFields.FieldRing = function(L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, opt_validator) {
    this.ringCell_ = [null, null, null, null, null, null, null, null, null, null, null, null];
    var value = {};
    value.L1 = L1 || this.DEFAULT_VALUE;
    value.L2 = L2 || this.DEFAULT_VALUE;
    value.L3 = L3 || this.DEFAULT_VALUE;
    value.L4 = L4 || this.DEFAULT_VALUE;
    value.L5 = L5 || this.DEFAULT_VALUE;
    value.L6 = L6 || this.DEFAULT_VALUE;
    value.L7 = L7 || this.DEFAULT_VALUE;
    value.L8 = L8 || this.DEFAULT_VALUE;
    value.L9 = L9 || this.DEFAULT_VALUE;
    value.L10 = L10 || this.DEFAULT_VALUE;
    value.L11 = L11 || this.DEFAULT_VALUE;
    value.L12 = L12 || this.DEFAULT_VALUE;

    CustomFields.FieldRing.superClass_.constructor.call(this, value, opt_validator);
    this.isDirty_ = true;
}
Blockly.utils.object.inherits(CustomFields.FieldRing, Blockly.Field);

CustomFields.FieldRing.fromJson = function(option) {
    return new CustomFields.FieldRing(
        option['L1'],
        option['L2'],
        option['L3'],
        option['L4'],
        option['L5'],
        option['L6'],
        option['L7'],
        option['L8'],
        option['L9'],
        option['L10'],
        option['L11'],
        option['L12']
    );
};

CustomFields.FieldRing.prototype.CURSOR = 'pointer';
CustomFields.FieldRing.prototype.SERIALIZABLE = true;
CustomFields.FieldRing.prototype.editorListeners_ = [];

CustomFields.FieldRing.COLOURS = [
    // grays
    '#ffffff', '#cccccc', '#c0c0c0', '#030303', '#666666', '#333333', '#000000',
    // reds
    '#ffcccc', '#ff6666', '#ff0000', '#cc0000', '#990000', '#660000', '#330000',
    // oranges
    '#ffcc99', '#ff9966', '#ff9900', '#ff6600', '#cc6600', '#993300', '#663300',
    // yellows
    '#ffff99', '#ffff66', '#ffcc66', '#ffcc33', '#cc9933', '#996633', '#663333',
    // olives
    '#ffffcc', '#ffff33', '#ffff00', '#ffcc00', '#999900', '#666600', '#333300',
    // greens
    '#99ff99', '#66ff99', '#33ff33', '#33cc00', '#009900', '#006600', '#003300',
    // turquoises
    '#99ffff', '#33ffff', '#66cccc', '#00cccc', '#339999', '#336666', '#003333',
    // blues
    '#ccffff', '#66ffff', '#33ccff', '#3366ff', '#3333ff', '#000099', '#000066',
    // purples
    '#ccccff', '#9999ff', '#6666cc', '#6633ff', '#6600cc', '#333399', '#330099',
    // violets
    '#ffccff', '#ff99ff', '#cc66cc', '#cc33cc', '#993399', '#663366', '#330033'
];

CustomFields.FieldRing.prototype.initView = function() {
    CustomFields.FieldRing.superClass_.initView.call(this);
    this.createView_();
}

CustomFields.FieldRing.prototype.DEFAULT_VALUE = CustomFields.FieldRing.COLOURS[3];

CustomFields.FieldRing.prototype.doClassValidation_ = function(newValue) {
    if (!this.fillValidColor(newValue.L1)) {
        newValue.L1 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L2)) {
        newValue.L2 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L3)) {
        newValue.L3 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L4)) {
        newValue.L4 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L5)) {
        newValue.L5 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L6)) {
        newValue.L6 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L7)) {
        newValue.L7 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L8)) {
        newValue.L8 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L9)) {
        newValue.L9 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L10)) {
        newValue.L10 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L11)) {
        newValue.L11 = this.DEFAULT_VALUE;
    }
    if (!this.fillValidColor(newValue.L12)) {
        newValue.L12 = this.DEFAULT_VALUE;
    }

    return newValue;
}

CustomFields.FieldRing.prototype.fillValidColor = function(color) {
    for (let i = 0; i < 10; i++) {
        if (CustomFields.FieldRing.COLOURS[3 + 7 * i] == color) {
            return true;
        }
    }
    return false;
}

CustomFields.FieldRing.prototype.showEditor_ = function() {
    this.curLed_ = null;
    this.editor_ = this.createWidgetView();
    this.colorEditor_ = this.createColorSelect();
    this.styleDiv();
    Blockly.WidgetDiv.DIV.appendChild(this.editor_);
    Blockly.WidgetDiv.DIV.appendChild(this.colorEditor_);
    this.renderEditor_();
    Blockly.WidgetDiv.show(this, this.sourceBlock_.LTR, this.widgetDispose_.bind(this));
}

CustomFields.FieldRing.prototype.styleDiv = function() {
    Blockly.WidgetDiv.DIV.style.width = "16em";
    Blockly.WidgetDiv.DIV.style.height = "20em";
    Blockly.WidgetDiv.DIV.style.left = "calc(50% - 8em)";
    Blockly.WidgetDiv.DIV.style.top = "calc(50% - 10em)";
    Blockly.WidgetDiv.DIV.style.backgroundColor = "#030303dc";
    Blockly.WidgetDiv.DIV.style.borderRadius = "2%";
    Blockly.WidgetDiv.DIV.style.border = "solid";
    Blockly.WidgetDiv.DIV.style.borderWidth = "1px";
    Blockly.WidgetDiv.DIV.style.color = "#a8d8f8";
    Blockly.WidgetDiv.DIV.style.boxShadow = "0 0 0.4em 0 #79c3f4";
    Blockly.WidgetDiv.DIV.style.textAlign = "center";
    Blockly.WidgetDiv.DIV.style.alignItems = "center";
    Blockly.WidgetDiv.DIV.style.justifyContent = "center";
}
CustomFields.FieldRing.prototype.RING_WIDTH = 11;

CustomFields.FieldRing.prototype.createWidgetView = function() {
    var editorDiv = document.createElement('div');
    editorDiv.className = 'editorRingLed';
    var ringText = document.createElement('div');
    ringText.textContent = "RING LED";
    ringText.id = "textRing";
    var ringDiv = document.createElement('div');
    ringDiv.className = "RingDiv";
    ringDiv.id = "RingDivParent";

    for (var i = 0; i < 12; i++) {
        var led = document.createElement('div');
        led.className = "ringLed";
        led.id = 'ringLed' + i.toString();
        var angle = 30 * i;
        var x = this.RING_WIDTH / 2 * Math.cos(Blockly.utils.math.toRadians(angle)) + 6.6;
        var y = this.RING_WIDTH / 2 * Math.sin(Blockly.utils.math.toRadians(angle)) + 6;
        led.style.position = 'absolute';
        led.style.left = x + 'em';
        led.style.top = y + 'em';
        led.style.transform = "rotate(" + angle + "deg)";

        var ledS = document.createElement('div');
        ledS.className = "ringLedS";
        ledS.style.position = 'absolute';
        ledS.style.left = x + 'em';
        ledS.style.top = y + 'em';
        ledS.style.transform = "rotate(" + angle + "deg)" + "translate(2px, -2px)";
        // ledS.style.transform = ;
        this.editorListeners_.push(Blockly.bindEvent_(led, this.getMouseUp(), this,
            this.onRingLedClick));

        ringDiv.appendChild(ledS);
        ringDiv.appendChild(led);
    }
    var core = document.createElement('div');
    core.id = 'coreRing';
    ringDiv.appendChild(core);

    var colorDiv = document.createElement('div');
    colorDiv.className = 'colorSec';

    editorDiv.appendChild(ringText);
    editorDiv.appendChild(ringDiv);
    editorDiv.appendChild(colorDiv);

    return editorDiv;
}

CustomFields.FieldRing.prototype.onRingLedClick = function(event) {
    this.curLed_ = event.target;
    for (let i = 0; i < 12; i++) {
        var led = document.getElementById('ringLed' + i.toString());
        led.style.boxShadow = '0 0 0.3em 0 #61a7d6';
    }

    this.curLed_.style.boxShadow = '0 0 0.8em 0 #61a7d6'
}

CustomFields.FieldRing.prototype.onColorButtonClick = function(event) {
    var color = {};
    color.L1 = this.value_.L1;
    color.L2 = this.value_.L2;
    color.L3 = this.value_.L3;
    color.L4 = this.value_.L4;
    color.L5 = this.value_.L5;
    color.L6 = this.value_.L6;
    color.L7 = this.value_.L7;
    color.L8 = this.value_.L8;
    color.L9 = this.value_.L9;
    color.L10 = this.value_.L10;
    color.L11 = this.value_.L11;
    color.L12 = this.value_.L12;
    const rgb2hex = (rgb) => `#${rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/).slice(1).map(n => parseInt(n, 10).toString(16).padStart(2, '0')).join('')}`
    if (this.curLed_) {
        var newColor = rgb2hex(event.target.style.backgroundColor);
        this.curLed_.style.backgroundColor = newColor;
        switch (this.curLed_.id) {
            case 'ringLed0':
                color.L1 = newColor;
                break;
            case 'ringLed1':
                color.L2 = newColor;
                break;
            case 'ringLed2':
                color.L3 = newColor;
                break;
            case 'ringLed3':
                color.L4 = newColor;
                break;
            case 'ringLed4':
                color.L5 = newColor;
                break;
            case 'ringLed5':
                color.L6 = newColor;
                break;
            case 'ringLed6':
                color.L7 = newColor;
                break;
            case 'ringLed7':
                color.L8 = newColor;
                break;
            case 'ringLed8':
                color.L9 = newColor;
                break;
            case 'ringLed9':
                color.L10 = newColor;
                break;
            case 'ringLed10':
                color.L11 = newColor;
                break;
            case 'ringLed11':
                color.L12 = newColor;
                break;
            case 'default':
                break;
        }
        this.isDirty_ = true;
        this.setValue(color);
    }
}

CustomFields.FieldRing.prototype.createColorSelect = function() {
    var ediColorDiv = document.createElement('div');
    ediColorDiv.id = 'editorRingColor';

    for (let i = 0; i < 10; i++) {
        var buttonDiv = document.createElement('div');
        buttonDiv.className = 'buttonDiv';

        var shadow = document.createElement('div');
        shadow.className = 'buttonShadow';

        var colorButton = document.createElement('div');
        colorButton.className = 'buttonColor';
        colorButton.style.backgroundColor = CustomFields.FieldRing.COLOURS[3 + i * 7];

        this.editorListeners_.push(Blockly.bindEvent_(colorButton, this.getMouseUp(), this,
            this.onColorButtonClick));
        buttonDiv.appendChild(shadow);
        buttonDiv.appendChild(colorButton);
        ediColorDiv.appendChild(buttonDiv);
    }

    return ediColorDiv;
}

CustomFields.FieldRing.prototype.updateSize_ = function() {
    var bbox = this.ringGroup_.getBBox();
    var width = bbox.width;
    var height = bbox.height;
    if (this.borderRect_) {
        this.borderRect_.setAttribute('width', width);
        this.borderRect_.setAttribute('height', height);
    }
    // Note how both the width and the height can be dynamic.
    this.size_.width = width;
    this.size_.height = height;
};

CustomFields.FieldRing.prototype.render_ = function() {
    var color;
    for (var i = 0; i < 12; i++) {
        switch (i) {
            case 0:
                color = this.value_.L1;
                break;
            case 1:
                color = this.value_.L2;
                break;
            case 2:
                color = this.value_.L3;
                break;
            case 3:
                color = this.value_.L4;
                break;
            case 4:
                color = this.value_.L5;
                break;
            case 5:
                color = this.value_.L6;
                break;
            case 6:
                color = this.value_.L7;
                break;
            case 7:
                color = this.value_.L8;
                break;
            case 8:
                color = this.value_.L9;
                break;
            case 9:
                color = this.value_.L10;
                break;
            case 10:
                color = this.value_.L11;
                break;
            case 11:
                color = this.value_.L12;
                break;
        }

        if (this.ringCell_[i]) {
            if (color == CustomFields.FieldRing.COLOURS[3]) {
                color = '#fff';
            }
            this.ringCell_[i].style.fill = color;
        }
    }
    this.updateSize_();
}

CustomFields.FieldRing.prototype.renderEditor_ = function() {
    var color;
    for (let i = 0; i < 12; i++) {
        var led = document.getElementById('ringLed' + i.toString());
        switch (i) {
            case 0:
                color = this.value_.L1;
                break;
            case 1:
                color = this.value_.L2;
                break;
            case 2:
                color = this.value_.L3;
                break;
            case 3:
                color = this.value_.L4;
                break;
            case 4:
                color = this.value_.L5;
                break;
            case 5:
                color = this.value_.L6;
                break;
            case 6:
                color = this.value_.L7;
                break;
            case 7:
                color = this.value_.L8;
                break;
            case 8:
                color = this.value_.L9;
                break;
            case 9:
                color = this.value_.L10;
                break;
            case 10:
                color = this.value_.L11;
                break;
            case 11:
                color = this.value_.L12;
                break;
        }
        led.style.backgroundColor = color;
    }
}

CustomFields.FieldRing.prototype.createView_ = function() {
    this.ringGroup_ = Blockly.utils.dom.createSvgElement('g', {
        'transform': 'translate(0, 0)'
    }, this.fieldGroup_);
    for (var i = 0; i < 12; i++) {
        var angle = 30 * i;
        var x = 80 * (1 + Math.cos(Blockly.utils.math.toRadians(angle)));
        var y = 80 * (1 + Math.sin(Blockly.utils.math.toRadians(angle)));
        if (!this.ringCell_[i]) {
            this.ringCell_[i] = Blockly.utils.dom.createSvgElement('rect', {
                'class': 'cellSvg',
                'x': x,
                'y': y,
                'width': 25,
                'height': 25,
                'rx': 5,
                'ry': 5,
                'fill': '#fff'
            }, this.ringGroup_);
        }
    }
}

CustomFields.FieldRing.prototype.widgetDispose_ = function() {
    for (var i = this.editorListeners_.length, listener; listener = this.editorListeners_[i]; i--) {
        Blockly.unbindEvent_(listener);
        this.editorListeners_.pop();
    }
}

CustomFields.FieldRing.prototype.toXml = function(fieldElement) {
    fieldElement.setAttribute('L1', this.value_.L1);
    fieldElement.setAttribute('L3', this.value_.L2);
    fieldElement.setAttribute('L4', this.value_.L3);
    fieldElement.setAttribute('L5', this.value_.L4);
    fieldElement.setAttribute('L2', this.value_.L5);
    fieldElement.setAttribute('L6', this.value_.L6);
    fieldElement.setAttribute('L7', this.value_.L7);
    fieldElement.setAttribute('L8', this.value_.L8);
    fieldElement.setAttribute('L9', this.value_.L9);
    fieldElement.setAttribute('L10', this.value_.L10);
    fieldElement.setAttribute('L11', this.value_.L11);
    fieldElement.setAttribute('L12', this.value_.L12);

    // Always return the element!
    return fieldElement;
};

CustomFields.FieldRing.prototype.fromXml = function(fieldElement) {
    // Because we had to do custom serialization for this field, we also need
    // to do custom de-serialization.

    var value = {};
    value.L1 = fieldElement.getAttribute('L1');
    value.L2 = fieldElement.getAttribute('L2');
    value.L3 = fieldElement.getAttribute('L3');
    value.L4 = fieldElement.getAttribute('L4');
    value.L5 = fieldElement.getAttribute('L5');
    value.L6 = fieldElement.getAttribute('L6');
    value.L7 = fieldElement.getAttribute('L7');
    value.L8 = fieldElement.getAttribute('L8');
    value.L9 = fieldElement.getAttribute('L9');
    value.L10 = fieldElement.getAttribute('L10');
    value.L11 = fieldElement.getAttribute('L11');
    value.L12 = fieldElement.getAttribute('L12');

    // The end goal is to call this.setValue()
    this.setValue(value);
};

CustomFields.FieldRing.prototype.detectMobile = function() {
    var result = (navigator.userAgent.match(/(iphone)|(ipod)|(ipad)|(android)|(blackberry)|(windows phone)|(symbian)/i));

    if (result !== null) {
        return "mobile";
    } else {
        return "desktop";
    }
}

CustomFields.FieldRing.prototype.getMouseDown = function() {
    if (this.detectMobile() == "desktop") {
        return "mousedown";
    } else {
        return "touchstart";
    }
}

CustomFields.FieldRing.prototype.getMouseUp = function() {
    if (this.detectMobile() == "desktop") {
        return "mouseup";
    } else {
        return "touchend";
    }
}

// add to tree
Blockly.fieldRegistry.register('field_ring', CustomFields.FieldRing);