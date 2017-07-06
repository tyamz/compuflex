<?php namespace Compuflex\Faqs;

use System\Classes\PluginBase;

class Plugin extends PluginBase
{
    public function registerComponents()
    {
      return [
        'Compuflex\Faqs\Components\Display' => 'displayFAQs'
      ];
    }

    public function registerSettings()
    {
    }
}
