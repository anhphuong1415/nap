'use strict'

goog.provide('CustomFields.FieldVelocity');

goog.require('Blockly.browserEvents');
goog.require('Blockly.FieldNumber');
goog.require('Blockly.Field');
goog.require('Blockly.fieldRegistry');
goog.require('Blockly.utils.math');
goog.require('Blockly.utils');
goog.require('Blockly.utils.dom');
goog.require('Blockly.utils.object');
goog.require('Blockly.utils.Size');

var CustomFields = CustomFields || {}

//contructor
CustomFields.FieldVelocity = function(opt_value, opt_min, opt_max, opt_precision) {
    this.max_ = opt_value;
    this.min_ = opt_min;
    this.value_ = opt_value;
    CustomFields.FieldVelocity.superClass_.constructor.call(this, opt_value, opt_min, opt_max, opt_precision);
}
Blockly.utils.object.inherits(CustomFields.FieldVelocity, Blockly.FieldNumber);

CustomFields.FieldVelocity.prototype.CURSOR = 'pointer';

CustomFields.FieldVelocity.prototype.initView = function() {
    CustomFields.FieldVelocity.superClass_.initView.call(this);
}

CustomFields.FieldVelocity.fromJson = function(options) {
    return new CustomFields.FieldVelocity(options['value'],
        undefined, undefined, undefined, undefined, options);
}

CustomFields.FieldVelocity.prototype.showEditor_ = function() {
    CustomFields.FieldVelocity.superClass_.showEditor_.call(this);
    this.WidgetCreate_();
    Blockly.WidgetDiv.show(
        this, this.sourceBlock_.RTL, this.widgetDispose_.bind(this));
}

CustomFields.FieldVelocity.prototype.WidgetCreate_ = function() {
    this.customWidget();
    this.editor_ = this.CreateWidget_();
    Blockly.WidgetDiv.DIV.appendChild(this.editor_);
}

CustomFields.FieldVelocity.prototype.customWidget = function() {
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

    var text_ = document.createElement('p');
    text_.id = 'text_vel';
    text_.textContent = 'Tốc độ: ';
    var inText = document.createElement('span');
    inText.className = 'current-value';
    inText.id = 'volumeValue';
    inText.textContent = '0';
    text_.appendChild(inText);
    Blockly.WidgetDiv.DIV.appendChild(text_);
}

CustomFields.FieldVelocity.prototype.CreateWidget_ = function() {

    var knod_surround = document.createElement('div');
    knod_surround.className = 'knob_surround';

    var knod = document.createElement('div');
    knod.className = 'knob';
    knod.id = 'knob';

    var min = document.createElement('span');
    min.className = 'min';
    min.textContent = "MIN";

    var max = document.createElement('span');
    max.className = 'max';
    max.textContent = 'MAX';

    var tickContainer = document.createElement('div');
    tickContainer.className = 'ticks';
    tickContainer.id = 'tickContainer';
    this.createTicks(tickContainer, 27, 0);

    knod_surround.appendChild(knod);
    knod_surround.appendChild(min);
    knod_surround.appendChild(max);
    knod_surround.appendChild(tickContainer);

    this.listener_ = Blockly.bindEvent_(knod, this.getMouseDown(), this, this.onMouseDown);
    // document.addEventListener(this.getMouseUp(), this.onMouseUp);
    this.mouseUpWrapper_ = Blockly.browserEvents.bind(document.body, this.getMouseUp(), this, this.onMouseUp);
    return knod_surround;
}

CustomFields.FieldVelocity.prototype.createTicks = function(tickContainer, numTicks, highlightNumTicks) {
    while (tickContainer.firstChild) {
        tickContainer.removeChild(tickContainer.firstChild);
    }

    var startingTickAngle = -135;
    //create ticks
    for (var i = 0; i < numTicks; i++) {
        var tick = document.createElement("div");

        //highlight only the appropriate ticks using dynamic CSS
        if (i < highlightNumTicks) {
            tick.className = "tick activetick";
        } else {
            tick.className = "tick";
        }

        tickContainer.appendChild(tick);
        tick.style.transform = "rotate(" + startingTickAngle + "deg)";
        startingTickAngle += 10;
    }

    startingTickAngle = -135; //reset

    // return tickContainer;
}

CustomFields.FieldVelocity.prototype.onMouseDown = function() {
    this.mouseMoveWrapper_ = Blockly.browserEvents.bind(document.body, this.getMouseMove(), this, this.onMouseMove);
}

CustomFields.FieldVelocity.prototype.onMouseUp = function() {
    if (this.mouseMoveWrapper_) {
        Blockly.browserEvents.unbind(this.mouseMoveWrapper_);
        this.mouseMoveWrapper_ = null;
    }
}

CustomFields.FieldVelocity.prototype.onMouseMove = function(event) {
    var volumeKnob = document.getElementById("knob")
    var tickContainer = document.getElementById("tickContainer");
    var boundingRectangle = volumeKnob.getBoundingClientRect();
    var knobPositionX;
    var knobPositionY;
    var mouseX;
    var mouseY;
    var knobCenterX;
    var knobCenterY;
    var adjacentSide;
    var oppositeSide;
    var currentRadiansAngle;
    var getRadiansInDegrees;
    var finalAngleInDegrees;
    var volumeSetting;
    var tickHighlightPosition;

    knobPositionX = boundingRectangle.left; //get knob's global x position
    knobPositionY = boundingRectangle.top; //get knob's global y position

    if (CustomFields.FieldVelocity.prototype.detectMobile() == "desktop") {
        mouseX = event.pageX; //get mouse's x global position
        mouseY = event.pageY; //get mouse's y global position
    } else {
        mouseX = event.touches[0].pageX; //get finger's x global position
        mouseY = event.touches[0].pageY; //get finger's y global position
    }

    knobCenterX = boundingRectangle.width / 2 + knobPositionX; //get global horizontal center position of knob relative to mouse position
    knobCenterY = boundingRectangle.height / 2 + knobPositionY; //get global vertical center position of knob relative to mouse position

    adjacentSide = knobCenterX - mouseX; //compute adjacent value of imaginary right angle triangle
    oppositeSide = knobCenterY - mouseY; //compute opposite value of imaginary right angle triangle

    //arc-tangent function returns circular angle in radians
    //use atan2() instead of atan() because atan() returns only 180 degree max (PI radians) but atan2() returns four quadrant's 360 degree max (2PI radians)
    currentRadiansAngle = Math.atan2(adjacentSide, oppositeSide);

    getRadiansInDegrees = currentRadiansAngle * 180 / Math.PI; //convert radians into degrees

    finalAngleInDegrees = -(getRadiansInDegrees - 135); //knob is already starting at -135 degrees due to visual design so 135 degrees needs to be subtracted to compensate for the angle offset, negative value represents clockwise direction

    //only allow rotate if greater than zero degrees or lesser than 270 degrees
    if (finalAngleInDegrees >= 0 && finalAngleInDegrees <= 270) {
        volumeKnob.style.transform = "rotate(" + finalAngleInDegrees + "deg)"; //use dynamic CSS transform to rotate volume knob

        //270 degrees maximum freedom of rotation / 100% volume = 1% of volume difference per 2.7 degrees of rotation
        volumeSetting = Math.floor(finalAngleInDegrees / (270 / 100));

        tickHighlightPosition = Math.round((volumeSetting * 2.7) / 10); //interpolate how many ticks need to be highlighted

        this.createTicks(tickContainer, 27, tickHighlightPosition); //highlight ticks

        document.getElementById("volumeValue").innerHTML = volumeSetting; //update volume text
        this.setDirty(volumeSetting);
    }
}

CustomFields.FieldVelocity.prototype.detectMobile = function() {
    var result = (navigator.userAgent.match(/(iphone)|(ipod)|(ipad)|(android)|(blackberry)|(windows phone)|(symbian)/i));

    if (result !== null) {
        return "mobile";
    } else {
        return "desktop";
    }
}

CustomFields.FieldVelocity.prototype.getMouseDown = function() {
    if (this.detectMobile() == "desktop") {
        return "mousedown";
    } else {
        return "touchstart";
    }
}

CustomFields.FieldVelocity.prototype.getMouseUp = function() {
    if (this.detectMobile() == "desktop") {
        return "mouseup";
    } else {
        return "touchend";
    }
}

CustomFields.FieldVelocity.prototype.getMouseMove = function() {
    if (this.detectMobile() == "desktop") {
        return "mousemove";
    } else {
        return "touchmove";
    }
}

CustomFields.FieldVelocity.prototype.setDirty = function(volumeSetting) {
    this.displayValue_ = volumeSetting;
    this.setValue(volumeSetting);
    this.isDirty_ = true;
}

CustomFields.FieldVelocity.prototype.widgetDispose_ = function() {
    if (this.listener_) {
        Blockly.unbindEvent_(this.listener_);
        this.listener_ = null;
    }

    if (this.mouseUpWrapper_) {
        Blockly.browserEvents.unbind(this.mouseUpWrapper_);
        this.mouseUpWrapper_ = null;
    }

    if (this.mouseMoveWrapper_) {
        Blockly.browserEvents.unbind(this.mouseMoveWrapper_);
        this.mouseMoveWrapper_ = null;
    }
    document.removeEventListener(this.getMouseUp(), this.onMouseUp);
};

CustomFields.FieldVelocity.prototype.showInlineEditor_ = function() {};

CustomFields.FieldVelocity.prototype.showPromptEditor_ = function() {};

CustomFields.FieldVelocity.prototype.doValueUpdate_ = function(newValue) {
    CustomFields.FieldVelocity.superClass_.doValueUpdate_.call(this, newValue);
    this.displayValue_ = newValue;
    this.isValueInvalid_ = false;
};

Blockly.fieldRegistry.register('field_velocity', CustomFields.FieldVelocity);