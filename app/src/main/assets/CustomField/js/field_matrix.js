'use strict';
// You must provide the constructor for your custom field.
goog.provide('CustomFields.FieldMatrix');

// You must require the abstract field class to inherit from.
goog.require('Blockly.browserEvents');
goog.require('Blockly.Field');
goog.require('Blockly.fieldRegistry');
goog.require('Blockly.utils');
goog.require('Blockly.utils.dom');
goog.require('Blockly.utils.object');
goog.require('Blockly.utils.Size');

var CustomFields = CustomFields || {};

CustomFields.FieldMatrix = function(R1, R2, R3, R4, R5, R6, R7, R8, opt_validator) {
    var value = {};
    value[0] = R1 || 0x00;
    value[1] = R2 || 0x00;
    value[2] = R3 || 0x00;
    value[3] = R4 || 0x00;
    value[4] = R5 || 0x00;
    value[5] = R6 || 0x00;
    value[6] = R7 || 0x00;
    value[7] = R8 || 0x00;

    CustomFields.FieldMatrix.superClass_.constructor.call(this, value, opt_validator);
    this.size_ = new Blockly.utils.Size(0, 0);
}
Blockly.utils.object.inherits(CustomFields.FieldMatrix, Blockly.Field);

// construct from JSON
CustomFields.FieldMatrix.fromJson = function(options) {
    return new CustomFields.FieldMatrix(
        options['R1'],
        options['R2'],
        options['R3'],
        options['R4'],
        options['R5'],
        options['R6'],
        options['R7'],
        options['R8']);
};

CustomFields.FieldMatrix.prototype.CURSOR = 'pointer';
CustomFields.FieldMatrix.prototype.SERIALIZABLE = true;
CustomFields.FieldMatrix.prototype.cellLed_ = [
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null]
];

CustomFields.FieldMatrix.prototype.initView = function() {
    CustomFields.FieldMatrix.superClass_.initView.call(this);
    this.createView_();
}

CustomFields.FieldMatrix.prototype.doClassValidation_ = function(newValue) {
    for (var i = 0; i < 8; i++) {
        if (newValue[0] == undefined || newValue[0] > 255 || newValue[0] < 0) {
            newValue[0] = 0x00;
        }
    }
    return newValue;
}

// CustomFields.FieldMatrix.prototype.doValueUpdate_ = function(newValue) {
//     CustomFields.FieldMatrix.superClass_.doValueUpdate_.call(this, newValue);
// }

CustomFields.FieldMatrix.prototype.showEditor_ = function() {
    this.editor_ = this.createWidgetView();
    this.styleDiv();
    Blockly.WidgetDiv.DIV.appendChild(this.editor_);
    this.renderEditor_();
    Blockly.WidgetDiv.show(
        this, this.sourceBlock_.LTR, this.widgetDispose_.bind(this));
}

CustomFields.FieldMatrix.prototype.styleDiv = function() {
    Blockly.WidgetDiv.DIV.style.width = "15em";
    Blockly.WidgetDiv.DIV.style.height = "16em";
    Blockly.WidgetDiv.DIV.style.left = "calc(50% - 7.5em)";
    Blockly.WidgetDiv.DIV.style.top = "calc(50% - 8em)";
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

CustomFields.FieldMatrix.prototype.createWidgetView = function() {
    var maxtrixDiv = document.createElement('div');
    maxtrixDiv.textContent = "MA TRẬN LED";
    maxtrixDiv.className = "matrixDiv";
    maxtrixDiv.id = "matrixDivParent";
    for (let i = 1; i < 9; i++) {
        var row = document.createElement('div');
        row.className = 'row';
        row.id = 'r' + i.toString();
        for (let j = 1; j < 9; j++) {
            var c = document.createElement('div');
            c.className = 'cell';
            c.id = 'r' + i.toString() + 'c' + j.toString();
            row.appendChild(c);
        }
        maxtrixDiv.appendChild(row);
    }
    this.mouseUpWrapper_ = Blockly.browserEvents.bind(document.body, this.getMouseUp(), this, this.onMouseUp);
    this.mouseDownWrapper_ = Blockly.browserEvents.bind(maxtrixDiv, this.getMouseDown(), this, this.onMouseDown);
    return maxtrixDiv;
}

CustomFields.FieldMatrix.prototype.onMouseDown = function(event) {
    this.onMouseMove(event);

    if (!this.mouseMoveWrapper_) {
        var node = document.getElementById('matrixDivParent');
        this.mouseMoveWrapper_ = Blockly.browserEvents.bind(node, this.getMouseMove(), this, this.onMouseMove);
    }
}

CustomFields.FieldMatrix.prototype.onMouseMove = function(event) {
    var mouseX;
    var mouseY;
    // var arrayLed = this.value_;

    if (CustomFields.FieldVelocity.prototype.detectMobile() == "desktop") {
        mouseX = event.pageX; //get mouse's x global position
        mouseY = event.pageY; //get mouse's y global position
    } else {
        mouseX = event.touches[0].pageX; //get finger's x global position
        mouseY = event.touches[0].pageY; //get finger's y global position
    }
    var curEl = document.elementFromPoint(mouseX, mouseY);
    if (this.curEl_ != curEl && curEl.className == 'cell') {
        this.curEl_ = curEl;
        var r = parseInt(curEl.id[1]);
        var c = parseInt(curEl.id[3]);
        if (this.curEl_.style.backgroundColor == 'transparent' || !this.curEl_.style.backgroundColor) {
            this.curEl_.style.backgroundColor = '#a8d8f8';
            this.value_[r - 1] = this.value_[r - 1] | (1 << (c - 1));
        } else {
            this.curEl_.style.backgroundColor = 'transparent'
            this.value_[r - 1] = this.value_[r - 1] & ~(1 << (c - 1));
        }

        //FIXME: change inblock view
        this.isDirty_ = true;
        this.setValue(this.value_);
    }
}

CustomFields.FieldMatrix.prototype.onMouseUp = function() {
    this.curEl_ = null;
    if (this.mouseMoveWrapper_) {
        Blockly.browserEvents.unbind(this.mouseMoveWrapper_);
        this.mouseMoveWrapper_ = null;
    }
}

CustomFields.FieldMatrix.prototype.updateSize_ = function() {
    var bbox = this.matrixGroup_.getBBox();
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

CustomFields.FieldMatrix.prototype.render_ = function() {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            if (this.value_[i] & (1 << j)) {
                this.cellLed_[i][j].style.fill = '#a8d8f8';
            } else {
                this.cellLed_[i][j].style.fill = '#fff';;
            }
        }
    }

    this.updateSize_();
}

CustomFields.FieldMatrix.prototype.renderEditor_ = function() {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            var id = 'r' + (i + 1).toString() + 'c' + (j + 1).toString();
            if (this.value_[i] & (1 << j)) {
                // this.cellLed_[i][j].style.fill = '#a8d8f8';
                document.getElementById(id).style.backgroundColor = '#a8d8f8';
            } else {
                // this.cellLed_[i][j].style.fill = '#fff';
                document.getElementById(id).style.backgroundColor = 'transparent';
            }
        }
    }
}

//
CustomFields.FieldMatrix.prototype.createView_ = function() {
    this.matrixGroup_ = Blockly.utils.dom.createSvgElement('g', {
        'transform': 'translate(0, 0)'
    }, this.fieldGroup_);
    for (var i = 0; i < 8; i++) {
        for (var j = 0; j < 8; j++) {
            this.cellLed_[i][j] = Blockly.utils.dom.createSvgElement('rect', {
                'class': 'cellSvg',
                'x': j * 20 + 1,
                'y': i * 20 + 1,
                'width': 20,
                'height': 20,
                'rx': 5,
                'ry': 5,
                'fill': '#fff'
            }, this.matrixGroup_);
        }
    }
}

CustomFields.FieldMatrix.prototype.widgetDispose_ = function() {
    if (this.mouseDownWrapper_) {
        Blockly.browserEvents.unbind(this.mouseDownWrapper_);
        this.mouseDownWrapper_ = null;
    }

    if (this.mouseUpWrapper_) {
        Blockly.browserEvents.unbind(this.mouseUpWrapper_);
        this.mouseUpWrapper_ = null;
    }

    if (this.mouseMoveWrapper_) {
        Blockly.browserEvents.unbind(this.mouseMoveWrapper_);
        this.mouseMoveWrapper_ = null;
    }
    this.curEl_ = null;
}

CustomFields.FieldMatrix.prototype.detectMobile = function() {
    var result = (navigator.userAgent.match(/(iphone)|(ipod)|(ipad)|(android)|(blackberry)|(windows phone)|(symbian)/i));

    if (result !== null) {
        return "mobile";
    } else {
        return "desktop";
    }
}

CustomFields.FieldMatrix.prototype.getMouseDown = function() {
    if (this.detectMobile() == "desktop") {
        return "mousedown";
    } else {
        return "touchstart";
    }
}

CustomFields.FieldMatrix.prototype.getMouseUp = function() {
    if (this.detectMobile() == "desktop") {
        return "mouseup";
    } else {
        return "touchend";
    }
}

CustomFields.FieldMatrix.prototype.getMouseMove = function() {
    if (this.detectMobile() == "desktop") {
        return "mousemove";
    } else {
        return "touchmove";
    }
}

// add to tree
Blockly.fieldRegistry.register('field_matrix', CustomFields.FieldMatrix);