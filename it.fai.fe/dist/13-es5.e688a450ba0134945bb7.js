!function(){function b(b,e){if(!(b instanceof e))throw new TypeError("Cannot call a class as a function")}function e(b,e){for(var t=0;t<e.length;t++){var o=e[t];o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(b,o.key,o)}}function t(b,t,o){return t&&e(b.prototype,t),o&&e(b,o),Object.defineProperty(b,"prototype",{writable:!1}),b}(window.webpackJsonp=window.webpackJsonp||[]).push([[13],{AgMk:function(e,o,d){"use strict";d.r(o),d.d(o,"ThemeModule",function(){return h});var n,i,a,r,c=d("SVse"),l=d("iInd"),s=d("NuRj"),V=d("8Y7J"),U=[{path:"",data:{title:"Theme"},children:[{path:"",redirectTo:"colors"},{path:"colors",component:(i=function(){function e(t){b(this,e),this._document=t}return t(e,[{key:"themeColors",value:function(){var b=this;Array.from(this._document.querySelectorAll(".theme-color")).forEach(function(e){var t=Object(s.getStyle)("background-color",e),o=b._document.createElement("table");o.innerHTML='\n        <table class="w-100">\n          <tr>\n            <td class="text-muted">HEX:</td>\n            <td class="font-weight-bold">'.concat(Object(s.rgbToHex)(t),'</td>\n          </tr>\n          <tr>\n            <td class="text-muted">RGB:</td>\n            <td class="font-weight-bold">').concat(t,"</td>\n          </tr>\n        </table>\n      "),e.parentNode.appendChild(o)})}},{key:"ngOnInit",value:function(){this.themeColors()}}]),e}(),i.\u0275fac=function(b){return new(b||i)(V.Pb(c.e))},i.\u0275cmp=V.Jb({type:i,selectors:[["ng-component"]],decls:131,vars:0,consts:[[1,"animated","fadeIn"],[1,"card"],[1,"card-header"],[1,"icon-drop"],[1,"card-body"],[1,"row"],[1,"col-xl-2","col-md-3","col-sm-4","col-6","mb-4"],[1,"bg-primary","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-secondary","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-success","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-danger","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-warning","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-info","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-light","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-dark","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"row","mb-3"],[1,"bg-gray-100","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-200","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-300","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-400","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-500","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-600","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-700","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-800","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-gray-900","theme-color","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-blue","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-light-blue","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-indigo","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-purple","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-pink","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-red","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-orange","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-yellow","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-green","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-teal","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"],[1,"bg-cyan","theme-color","mb-3","w-75","rounded","mb-2",2,"padding-top","75%"]],template:function(b,e){1&b&&(V.Vb(0,"div",0),V.Vb(1,"div",1),V.Vb(2,"div",2),V.Qb(3,"i",3),V.Kc(4," Theme colors "),V.Ub(),V.Vb(5,"div",4),V.Vb(6,"div",5),V.Vb(7,"div",6),V.Qb(8,"div",7),V.Vb(9,"h6"),V.Kc(10,"Brand Primary Color"),V.Ub(),V.Ub(),V.Vb(11,"div",6),V.Qb(12,"div",8),V.Vb(13,"h6"),V.Kc(14,"Brand Secondary Color"),V.Ub(),V.Ub(),V.Vb(15,"div",6),V.Qb(16,"div",9),V.Vb(17,"h6"),V.Kc(18,"Brand Success Color"),V.Ub(),V.Ub(),V.Vb(19,"div",6),V.Qb(20,"div",10),V.Vb(21,"h6"),V.Kc(22,"Brand Danger Color"),V.Ub(),V.Ub(),V.Vb(23,"div",6),V.Qb(24,"div",11),V.Vb(25,"h6"),V.Kc(26,"Brand Warning Color"),V.Ub(),V.Ub(),V.Vb(27,"div",6),V.Qb(28,"div",12),V.Vb(29,"h6"),V.Kc(30,"Brand Info Color"),V.Ub(),V.Ub(),V.Vb(31,"div",6),V.Qb(32,"div",13),V.Vb(33,"h6"),V.Kc(34,"Brand Light Color"),V.Ub(),V.Ub(),V.Vb(35,"div",6),V.Qb(36,"div",14),V.Vb(37,"h6"),V.Kc(38,"Brand Dark Color"),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Vb(39,"div",1),V.Vb(40,"div",2),V.Qb(41,"i",3),V.Kc(42," Grays "),V.Ub(),V.Vb(43,"div",4),V.Vb(44,"div",15),V.Vb(45,"div",6),V.Qb(46,"div",16),V.Vb(47,"h6"),V.Kc(48,"Gray 100 Color"),V.Ub(),V.Ub(),V.Vb(49,"div",6),V.Qb(50,"div",17),V.Vb(51,"h6"),V.Kc(52,"Gray 200 Color"),V.Ub(),V.Ub(),V.Vb(53,"div",6),V.Qb(54,"div",18),V.Vb(55,"h6"),V.Kc(56,"Gray 300 Color"),V.Ub(),V.Ub(),V.Vb(57,"div",6),V.Qb(58,"div",19),V.Vb(59,"h6"),V.Kc(60,"Gray 400 Color"),V.Ub(),V.Ub(),V.Vb(61,"div",6),V.Qb(62,"div",20),V.Vb(63,"h6"),V.Kc(64,"Gray 500 Color"),V.Ub(),V.Ub(),V.Vb(65,"div",6),V.Qb(66,"div",21),V.Vb(67,"h6"),V.Kc(68,"Gray 600 Color"),V.Ub(),V.Ub(),V.Vb(69,"div",6),V.Qb(70,"div",22),V.Vb(71,"h6"),V.Kc(72,"Gray 700 Color"),V.Ub(),V.Ub(),V.Vb(73,"div",6),V.Qb(74,"div",23),V.Vb(75,"h6"),V.Kc(76,"Gray 800 Color"),V.Ub(),V.Ub(),V.Vb(77,"div",6),V.Qb(78,"div",24),V.Vb(79,"h6"),V.Kc(80,"Gray 900 Color"),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Vb(81,"div",1),V.Vb(82,"div",2),V.Qb(83,"i",3),V.Kc(84," Additional colors "),V.Ub(),V.Vb(85,"div",4),V.Vb(86,"div",5),V.Vb(87,"div",6),V.Qb(88,"div",25),V.Vb(89,"h6"),V.Kc(90,"Blue Color"),V.Ub(),V.Ub(),V.Vb(91,"div",6),V.Qb(92,"div",26),V.Vb(93,"h6"),V.Kc(94,"Light Blue Color"),V.Ub(),V.Ub(),V.Vb(95,"div",6),V.Qb(96,"div",27),V.Vb(97,"h6"),V.Kc(98,"Indigo Color"),V.Ub(),V.Ub(),V.Vb(99,"div",6),V.Qb(100,"div",28),V.Vb(101,"h6"),V.Kc(102,"Purple Color"),V.Ub(),V.Ub(),V.Vb(103,"div",6),V.Qb(104,"div",29),V.Vb(105,"h6"),V.Kc(106,"Pink Color"),V.Ub(),V.Ub(),V.Vb(107,"div",6),V.Qb(108,"div",30),V.Vb(109,"h6"),V.Kc(110,"Red Color"),V.Ub(),V.Ub(),V.Vb(111,"div",6),V.Qb(112,"div",31),V.Vb(113,"h6"),V.Kc(114,"Orange Color"),V.Ub(),V.Ub(),V.Vb(115,"div",6),V.Qb(116,"div",32),V.Vb(117,"h6"),V.Kc(118,"Yellow Color"),V.Ub(),V.Ub(),V.Vb(119,"div",6),V.Qb(120,"div",33),V.Vb(121,"h6"),V.Kc(122,"Green Color"),V.Ub(),V.Ub(),V.Vb(123,"div",6),V.Qb(124,"div",34),V.Vb(125,"h6"),V.Kc(126,"Teal Color"),V.Ub(),V.Ub(),V.Vb(127,"div",6),V.Qb(128,"div",35),V.Vb(129,"h6"),V.Kc(130,"Cyan Color"),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub())},encapsulation:2}),i),data:{title:"Colors"}},{path:"typography",component:(n=t(function e(){b(this,e)}),n.\u0275fac=function(b){return new(b||n)},n.\u0275cmp=V.Jb({type:n,selectors:[["ng-component"]],decls:189,vars:0,consts:[[1,"animated","fadeIn"],[1,"card"],[1,"card-header"],[1,"card-body"],[1,"table"],[1,"highlighter-rouge"],[1,"h1"],[1,"h2"],[1,"h3"],[1,"h4"],[1,"h5"],[1,"h6"],[1,"bd-example"],[1,"bd-example","bd-example-type"],[1,"display-1"],[1,"display-2"],[1,"display-3"],[1,"display-4"],[1,"row"],[1,"col-sm-3"],[1,"col-sm-9"],[1,"col-sm-3","text-truncate"],[1,"col-sm-4"],[1,"col-sm-8"]],template:function(b,e){1&b&&(V.Vb(0,"div",0),V.Vb(1,"div",1),V.Vb(2,"div",2),V.Kc(3," Headings "),V.Ub(),V.Vb(4,"div",3),V.Vb(5,"p"),V.Kc(6,"Documentation and examples for Bootstrap typography, including global settings, headings, body text, lists, and more."),V.Ub(),V.Vb(7,"table",4),V.Vb(8,"thead"),V.Vb(9,"tr"),V.Vb(10,"th"),V.Kc(11,"Heading"),V.Ub(),V.Vb(12,"th"),V.Kc(13,"Example"),V.Ub(),V.Ub(),V.Ub(),V.Vb(14,"tbody"),V.Vb(15,"tr"),V.Vb(16,"td"),V.Vb(17,"p"),V.Vb(18,"code",5),V.Kc(19,"<h1></h1>"),V.Ub(),V.Ub(),V.Ub(),V.Vb(20,"td"),V.Vb(21,"span",6),V.Kc(22,"h1. Bootstrap heading"),V.Ub(),V.Ub(),V.Ub(),V.Vb(23,"tr"),V.Vb(24,"td"),V.Vb(25,"p"),V.Vb(26,"code",5),V.Kc(27,"<h2></h2>"),V.Ub(),V.Ub(),V.Ub(),V.Vb(28,"td"),V.Vb(29,"span",7),V.Kc(30,"h2. Bootstrap heading"),V.Ub(),V.Ub(),V.Ub(),V.Vb(31,"tr"),V.Vb(32,"td"),V.Vb(33,"p"),V.Vb(34,"code",5),V.Kc(35,"<h3></h3>"),V.Ub(),V.Ub(),V.Ub(),V.Vb(36,"td"),V.Vb(37,"span",8),V.Kc(38,"h3. Bootstrap heading"),V.Ub(),V.Ub(),V.Ub(),V.Vb(39,"tr"),V.Vb(40,"td"),V.Vb(41,"p"),V.Vb(42,"code",5),V.Kc(43,"<h4></h4>"),V.Ub(),V.Ub(),V.Ub(),V.Vb(44,"td"),V.Vb(45,"span",9),V.Kc(46,"h4. Bootstrap heading"),V.Ub(),V.Ub(),V.Ub(),V.Vb(47,"tr"),V.Vb(48,"td"),V.Vb(49,"p"),V.Vb(50,"code",5),V.Kc(51,"<h5></h5>"),V.Ub(),V.Ub(),V.Ub(),V.Vb(52,"td"),V.Vb(53,"span",10),V.Kc(54,"h5. Bootstrap heading"),V.Ub(),V.Ub(),V.Ub(),V.Vb(55,"tr"),V.Vb(56,"td"),V.Vb(57,"p"),V.Vb(58,"code",5),V.Kc(59,"<h6></h6>"),V.Ub(),V.Ub(),V.Ub(),V.Vb(60,"td"),V.Vb(61,"span",11),V.Kc(62,"h6. Bootstrap heading"),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Vb(63,"div",1),V.Vb(64,"div",2),V.Kc(65," Headings "),V.Ub(),V.Vb(66,"div",3),V.Vb(67,"p"),V.Vb(68,"code",5),V.Kc(69,".h1"),V.Ub(),V.Kc(70," through "),V.Vb(71,"code",5),V.Kc(72,".h6"),V.Ub(),V.Kc(73," classes are also available, for when you want to match the font styling of a heading but cannot use the associated HTML element."),V.Ub(),V.Vb(74,"div",12),V.Vb(75,"p",6),V.Kc(76,"h1. Bootstrap heading"),V.Ub(),V.Vb(77,"p",7),V.Kc(78,"h2. Bootstrap heading"),V.Ub(),V.Vb(79,"p",8),V.Kc(80,"h3. Bootstrap heading"),V.Ub(),V.Vb(81,"p",9),V.Kc(82,"h4. Bootstrap heading"),V.Ub(),V.Vb(83,"p",10),V.Kc(84,"h5. Bootstrap heading"),V.Ub(),V.Vb(85,"p",11),V.Kc(86,"h6. Bootstrap heading"),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Vb(87,"div",1),V.Vb(88,"div",2),V.Kc(89," Display headings "),V.Ub(),V.Vb(90,"div",3),V.Vb(91,"p"),V.Kc(92,"Traditional heading elements are designed to work best in the meat of your page content. When you need a heading to stand out, consider using a "),V.Vb(93,"strong"),V.Kc(94,"display heading"),V.Ub(),V.Kc(95,"\u2014a larger, slightly more opinionated heading style."),V.Ub(),V.Vb(96,"div",13),V.Vb(97,"table",4),V.Vb(98,"tbody"),V.Vb(99,"tr"),V.Vb(100,"td"),V.Vb(101,"span",14),V.Kc(102,"Display 1"),V.Ub(),V.Ub(),V.Ub(),V.Vb(103,"tr"),V.Vb(104,"td"),V.Vb(105,"span",15),V.Kc(106,"Display 2"),V.Ub(),V.Ub(),V.Ub(),V.Vb(107,"tr"),V.Vb(108,"td"),V.Vb(109,"span",16),V.Kc(110,"Display 3"),V.Ub(),V.Ub(),V.Ub(),V.Vb(111,"tr"),V.Vb(112,"td"),V.Vb(113,"span",17),V.Kc(114,"Display 4"),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Vb(115,"div",1),V.Vb(116,"div",2),V.Kc(117," Inline text elements "),V.Ub(),V.Vb(118,"div",3),V.Vb(119,"p"),V.Kc(120,"Traditional heading elements are designed to work best in the meat of your page content. When you need a heading to stand out, consider using a "),V.Vb(121,"strong"),V.Kc(122,"display heading"),V.Ub(),V.Kc(123,"\u2014a larger, slightly more opinionated heading style."),V.Ub(),V.Vb(124,"div",12),V.Vb(125,"p"),V.Kc(126,"You can use the mark tag to "),V.Vb(127,"mark"),V.Kc(128,"highlight"),V.Ub(),V.Kc(129," text."),V.Ub(),V.Vb(130,"p"),V.Vb(131,"del"),V.Kc(132,"This line of text is meant to be treated as deleted text."),V.Ub(),V.Ub(),V.Vb(133,"p"),V.Vb(134,"s"),V.Kc(135,"This line of text is meant to be treated as no longer accurate."),V.Ub(),V.Ub(),V.Vb(136,"p"),V.Vb(137,"ins"),V.Kc(138,"This line of text is meant to be treated as an addition to the document."),V.Ub(),V.Ub(),V.Vb(139,"p"),V.Vb(140,"u"),V.Kc(141,"This line of text will render as underlined"),V.Ub(),V.Ub(),V.Vb(142,"p"),V.Vb(143,"small"),V.Kc(144,"This line of text is meant to be treated as fine print."),V.Ub(),V.Ub(),V.Vb(145,"p"),V.Vb(146,"strong"),V.Kc(147,"This line rendered as bold text."),V.Ub(),V.Ub(),V.Vb(148,"p"),V.Vb(149,"em"),V.Kc(150,"This line rendered as italicized text."),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Vb(151,"div",1),V.Vb(152,"div",2),V.Kc(153," Description list alignment "),V.Ub(),V.Vb(154,"div",3),V.Vb(155,"p"),V.Kc(156,"Align terms and descriptions horizontally by using our grid system\u2019s predefined classes (or semantic mixins). For longer terms, you can optionally add a "),V.Vb(157,"code",5),V.Kc(158,".text-truncate"),V.Ub(),V.Kc(159," class to truncate the text with an ellipsis."),V.Ub(),V.Vb(160,"div",12),V.Vb(161,"dl",18),V.Vb(162,"dt",19),V.Kc(163,"Description lists"),V.Ub(),V.Vb(164,"dd",20),V.Kc(165,"A description list is perfect for defining terms."),V.Ub(),V.Vb(166,"dt",19),V.Kc(167,"Euismod"),V.Ub(),V.Vb(168,"dd",20),V.Vb(169,"p"),V.Kc(170,"Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit."),V.Ub(),V.Vb(171,"p"),V.Kc(172,"Donec id elit non mi porta gravida at eget metus."),V.Ub(),V.Ub(),V.Vb(173,"dt",19),V.Kc(174,"Malesuada porta"),V.Ub(),V.Vb(175,"dd",20),V.Kc(176,"Etiam porta sem malesuada magna mollis euismod."),V.Ub(),V.Vb(177,"dt",21),V.Kc(178,"Truncated term is truncated"),V.Ub(),V.Vb(179,"dd",20),V.Kc(180,"Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus."),V.Ub(),V.Vb(181,"dt",19),V.Kc(182,"Nesting"),V.Ub(),V.Vb(183,"dd",20),V.Vb(184,"dl",18),V.Vb(185,"dt",22),V.Kc(186,"Nested definition list"),V.Ub(),V.Vb(187,"dd",23),V.Kc(188,"Aenean posuere, tortor sed cursus feugiat, nunc augue blandit nunc."),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub(),V.Ub())},encapsulation:2}),n),data:{title:"Typography"}}]}],p=((r=t(function e(){b(this,e)})).\u0275fac=function(b){return new(b||r)},r.\u0275mod=V.Nb({type:r}),r.\u0275inj=V.Mb({imports:[[l.g.forChild(U)],l.g]}),r),h=((a=t(function e(){b(this,e)})).\u0275fac=function(b){return new(b||a)},a.\u0275mod=V.Nb({type:a}),a.\u0275inj=V.Mb({imports:[[c.c,p]]}),a)}}])}();