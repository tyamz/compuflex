<?php namespace Compuflex\Contact\Controllers;

use Backend\Classes\Controller;
use BackendMenu;

class Messages extends Controller
{
    public $implement = ['Backend\Behaviors\ListController'];
    
    public $listConfig = 'config_list.yaml';

    public function __construct()
    {
        parent::__construct();
        BackendMenu::setContext('Compuflex.Contact', 'contact', 'messages');
    }
}