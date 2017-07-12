<?php namespace Compuflex\Company\Models;

use Model;

/**
 * Model
 */
class Company extends Model
{
    use \October\Rain\Database\Traits\Validation;

    /*
     * Disable timestamps by default.
     * Remove this line if timestamps are defined in the database table.
     */
    public $timestamps = false;

    /*
     * Validation
     */
    public $rules = [
    ];

    /**
     * @var string The database table used by the model.
     */
    public $table = 'compuflex_company_';

    public $hasMany = [
      'users' => ['Rainlab\User\Models\User'],
      'questions' => ['Compuflex\Faqs\Models\Questions']
    ];

    public $belongsToMany = [
      'file' => ['Compuflex\Downloads\Models\File']
    ];
}
