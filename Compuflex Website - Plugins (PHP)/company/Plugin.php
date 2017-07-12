<?php namespace Compuflex\Company;

use System\Classes\PluginBase;

class Plugin extends PluginBase
{
    public function registerComponents()
    {
      return [
        'Compuflex\Company\Components\Info' => 'companyInfo'
      ];
    }

    public function registerSettings()
    {
    }
}
