<?php namespace Compuflex\Downloads;

use System\Classes\PluginBase;

class Plugin extends PluginBase
{

    /**
     * Returns information about this plugin.
     *
     * @return array
     */
    public function pluginDetails()
    {
        return [
            'name'        => 'User Downloads',
            'description' => 'User Download Manager',
            'author'      => 'Thomas Yamakaitis (for The Compuflex Corporation - © 2017 The Compuflex Inc. All rights reserved.',
            'icon'        => 'icon-download'
        ];
    }

    /**
     * Registers any front-end components implemented in this plugin.
     *
     * @return array
     */
    public function registerComponents()
    {
        return [
            '\Compuflex\Downloads\Components\Downloads' => 'downloads'
        ];
    }
}
