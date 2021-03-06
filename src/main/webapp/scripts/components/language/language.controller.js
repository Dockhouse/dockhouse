/*
 *  Copyright (C) 2015  Dockhouse project org. ( http://dockhouse.github.io/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
(function (){
    "use strict";

    angular
        .module('dockhouseApp')
        .controller('LanguageController',LanguageController);

    LanguageController.$inject = ['$translate', 'Language'];

    /* @ngInject */
    function LanguageController($translate, Language){
        /* jshint validthis: true */
        var vm = this;

        vm.languages = '';
        vm.changeLanguage = changeLanguage;
        vm.activate = activate;

        activate();

        ////////////////

        function activate(){
            Language.getAll().then(function (languages) {
                vm.languages = languages;
            });
        }

        function changeLanguage(languageKey) {
            $translate.use(languageKey);
        }
    }
})();

