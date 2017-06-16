<?php namespace Compuflex\Downloads\Models;

use Model;
use October\Rain\Database\Attach\File as FileBase;

/**
 * Model
 */
class File extends FileBase
{
    use \October\Rain\Database\Traits\Validation;

    /*
     * Validation
     */
    public $rules = [
    ];

    /*
     * Disable timestamps by default.
     * Remove this line if timestamps are defined in the database table.
     */
    public $timestamps = false;

    /**
     * @var string The database table used by the model.
     */
    public $table = 'compuflex_downloads_files';


    public $attachOne = [
            'file' => ['System\Models\File', 
				'key' => 'id']
    ];
	
	public $belongsToMany = [
			'user' => ['RainLab\User\Models\User']
	];

}